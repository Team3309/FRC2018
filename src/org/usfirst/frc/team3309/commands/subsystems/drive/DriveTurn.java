package org.usfirst.frc.team3309.commands.subsystems.drive;

import org.usfirst.frc.team3309.lib.CommandEx;
import org.usfirst.frc.team3309.lib.LibTimer;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDController;
import org.usfirst.frc.team3309.lib.math.LibMath;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveTurn extends CommandEx {

    private double goalAngle;
    private PIDController angleController;
    private final double ANGLE_LENIENCY = 3; // 1.5
    private boolean isInitialized = false;
    private LibTimer timer = new LibTimer(0.35);
    private double timeoutSec = Double.POSITIVE_INFINITY;
    private boolean isPigeon = false;

    public DriveTurn(double goalAngle) {
        this.goalAngle = goalAngle;
        angleController = new PIDController(new PIDConstants(0.0088, 0.0000, 0.00001));
        requires(Robot.drive);
    }

    public DriveTurn(double goalAngle, double timeoutSec) {
        this(goalAngle);
        this.timeoutSec = timeoutSec;
    }

    public DriveTurn(double goalAngle, double timeoutSec, boolean isPigeon) {
        this(goalAngle, timeoutSec);
        this.isPigeon = isPigeon;
    }

    @Override
    public void initialize() {
        super.initialize();
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
        double power = angleController.update(isPigeon ? Robot.drive.getPigeonPos() : Robot.drive.getAngPos(), goalAngle);
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
        super.end();
        isInitialized = false;
        timeoutSec = Double.POSITIVE_INFINITY;
    }

}

