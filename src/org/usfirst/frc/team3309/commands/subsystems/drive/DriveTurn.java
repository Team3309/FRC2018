package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.LibTimer;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDController;
import org.usfirst.frc.team3309.lib.math.LibMath;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveTurn extends Command {

    private double goalAngle;
    private PIDController angleController;
    private final double ANGLE_LENIENCY = 15; // 1.5
    private boolean isInitialized = false;
    private LibTimer timer = new LibTimer(0.5);

    public DriveTurn(double goalAngle) {
        this.goalAngle = goalAngle;

        // compbot constants 0.0077, 0, 0.00001
        angleController = new PIDController(new PIDConstants(0.0094, 0.000042, 0.0));
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
            initialize();
        }
        double power = angleController.update(Robot.drive.getAngPos(), goalAngle);
        Robot.drive.setLeftRight(power, -power);
    }

    @Override
    protected boolean isFinished() {
        return timer.isConditionMaintained(
                LibMath.isWithin(Robot.drive.getAngPos(),
                        goalAngle - ANGLE_LENIENCY, goalAngle + ANGLE_LENIENCY));
    }

    @Override
    public void end() {
        isInitialized = false;
    }

}
