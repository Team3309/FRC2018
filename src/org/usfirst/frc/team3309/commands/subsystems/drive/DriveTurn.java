package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDController;
import org.usfirst.frc.team3309.lib.math.LibMath;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveTurn extends Command {

    private double goalAngle;
    private PIDController angleController;
    private final double ANGLE_LENIENCY = 3;
    private boolean isInitialized = false;

    public DriveTurn(double goalAngle) {
        this.goalAngle = goalAngle;
        angleController = new PIDController(new PIDConstants(0.0077, 0, 0.00001));
        requires(Robot.drive);
    }

    @Override
    public void initialize() {
        isInitialized = true;
        Robot.drive.reset();
        Robot.drive.setHighGear();
        Robot.drive.changeToBrakeMode();
        Robot.drive.changeToPercentMode();
    }

    @Override
    protected void execute() {
        if (!isInitialized) {
            isInitialized = true;
        }
        double power = angleController.update(Robot.drive.getAngPos(), goalAngle);
        Robot.drive.setLeftRight(power, -power);
    }

    @Override
    protected boolean isFinished() {
        return LibMath.isWithin(Robot.drive.getAngPos(), goalAngle - ANGLE_LENIENCY, goalAngle + ANGLE_LENIENCY);
    }

    @Override
    public void end() {
        isInitialized = false;
    }

}
