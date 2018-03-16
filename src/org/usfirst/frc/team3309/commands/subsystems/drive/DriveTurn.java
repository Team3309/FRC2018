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
    private final double ANGLE_LENIENCY = 5; // 1.5
    private boolean isInitialized = false;
    private LibTimer timer = new LibTimer(0.35);
    private double timeoutSec = Double.POSITIVE_INFINITY;

    public DriveTurn(double goalAngle) {
        this.goalAngle = goalAngle;

        // compbot constants 0.0077, 0, 0.00001
        angleController = new PIDController(new PIDConstants(0.0076, 0.0000, 0.00001));
        requires(Robot.drive);
    }

    public DriveTurn(double goalAngle, double timeoutSec) {
        this(goalAngle);
        this.timeoutSec = timeoutSec;
    }

    @Override
    public void initialize() {
        isInitialized = true;
        Robot.drive.reset();
        Robot.drive.setHighGear();
        Robot.drive.changeToBrakeMode();
        Robot.drive.changeToPercentMode();
        timer.reset();
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
                        goalAngle - ANGLE_LENIENCY, goalAngle + ANGLE_LENIENCY))
                || timer.get() > timeoutSec;
    }

    @Override
    public void end() {
        isInitialized = false;
        timeoutSec = Double.POSITIVE_INFINITY;
    }

}

