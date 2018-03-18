package org.usfirst.frc.team3309.commands.subsystems.drive;

import org.usfirst.frc.team3309.lib.CommandEx;
import org.usfirst.frc.team3309.lib.LibTimer;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveStraight extends CommandEx {

    // inches
    private final double goalPos;
    private double error;
    private boolean isMotionProfile = false;

    private double timeoutSec = Double.POSITIVE_INFINITY;

    private final double errorThreshold = 6;
    private int CRUISE_VELOCITY = 25000;

    private LibTimer timer = new LibTimer(.5);

    private boolean isInitialized = false;

    public DriveStraight(Length goalPos) {
        this.goalPos = goalPos.toInches();
        requires(Robot.drive);
    }

    public DriveStraight(Length goalPos, boolean isMotionProfile) {
        this(goalPos);
        this.isMotionProfile = isMotionProfile;
    }

    public DriveStraight(Length goalPos, double timeoutSec) {
        this(goalPos);
        this.timeoutSec = timeoutSec;
    }

    public DriveStraight(Length goalPos, boolean isMotionProfile, double timeoutSec) {
        this(goalPos, timeoutSec);
        this.isMotionProfile = isMotionProfile;
    }

    @Override
    public void initialize() {
        Robot.drive.reset();
        Robot.drive.setHighGear();
        Robot.drive.changeToBrakeMode();
        Robot.drive.setGoalPos(goalPos);
        if (isMotionProfile) {
            Robot.drive.changeToMotionMagicMode();
        } else {
            Robot.drive.changeToPositionMode();
        }
        timer.reset();
        isInitialized = true;
    }

    @Override
    protected void execute() {
        if (!isInitialized) {
            initialize();
        }
        error = Robot.drive.getGoalPos() - Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos());
        Robot.drive.setLeftRight(-Robot.drive.inchesToEncoderCounts(Robot.drive.getGoalPos()),
                -Robot.drive.inchesToEncoderCounts(Robot.drive.getGoalPos()));
        if (isMotionProfile) {
            Robot.drive.configLeftRightCruiseVelocity(CRUISE_VELOCITY, CRUISE_VELOCITY);
        }
    }

    @Override
    protected boolean isFinished() {
        return timer.isConditionMaintained(Math.abs(error) < errorThreshold)
                || timer.get() > timeoutSec;
    }

    @Override
    public void end() {
        isInitialized = false;
        timeoutSec = Double.POSITIVE_INFINITY;
        isMotionProfile = false;
    }

}
