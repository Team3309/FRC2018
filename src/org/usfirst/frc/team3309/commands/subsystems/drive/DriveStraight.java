package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.lib.CommandEx;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDController;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveStraight extends CommandEx
{

    private boolean isInit = false;
    private boolean isAbs = false;
    private double startAngleVel;
    private double start;
    private double timeout = Double.POSITIVE_INFINITY;
    private PIDController angleController = new PIDController(new PIDConstants(0.6, 0, 0));

    public enum DriveStrategy {
        VELOCITY,
        MOTION_MAGIC,
        POSITION,
    }

    private DriveStrategy strategy;
    private double distance;
    private int velocityTarget = 15000;
    private boolean allowOvershoot = false;

    public DriveStraight(double distance, DriveStrategy strategy)
    {
        this.strategy = strategy;
        this.distance = Robot.drive.inchesToEncoderCounts(distance);
        requires(Robot.drive);
    }

    public DriveStraight(double distance, int velocity) {
        this(distance, DriveStrategy.VELOCITY);
        this.velocityTarget = velocity;
    }

    public DriveStraight(double distance, int velocity, boolean allowOvershoot, boolean isAbs) {
        this(distance, velocity, allowOvershoot);
        this.isAbs = isAbs;
    }

    public DriveStraight(double distance, int velocity, boolean allowOvershoot) {
        this(distance, velocity);
        this.allowOvershoot = allowOvershoot;
    }

    public DriveStraight(double distance, int velocity, boolean allowOvershoot, double timeout) {
        this(distance, velocity, allowOvershoot);
        this.timeout = timeout;
    }

    public DriveStraight(double distance, int velocity, double timeout) {
        this(distance, velocity, false, timeout);
    }

    @Override
    public void initialize() {
        super.initialize();
        if (!isAbs) {
            Robot.drive.reset();
        }
        isInit = true;
        start = Timer.getFPGATimestamp();
        startAngleVel = Robot.drive.getAngVel();
    }

    @Override
    protected void execute()
    {
        if (!isInit) {
            initialize();
        }
        SmartDashboard.putNumber("error: ", Math.abs(distance-Robot.drive.getEncoderPos()));
        switch(strategy)
        {
            case POSITION:
                Robot.drive.changeToPositionMode();
                if (isAbs) {
                    Robot.drive.setLeftRight(Robot.drive.getLeftEncoder() + distance,Robot.drive.getRightEncoder() + distance);
                } else {
                    Robot.drive.setLeftRight(distance, distance);
                }
                break;
            case VELOCITY:
                Robot.drive.changeToVelocityMode();
                double angularUpdate = -30000 * angleController.update(Robot.drive.getAngVel(), startAngleVel);
                if(distance > Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos()))
                    Robot.drive.setLeftRight(velocityTarget + angularUpdate,velocityTarget - angularUpdate);
                else
                    Robot.drive.setLeftRight(-velocityTarget + angularUpdate,-velocityTarget - angularUpdate);
                break;
            case MOTION_MAGIC:
                Robot.drive.changeToMotionMagicMode();
                Robot.drive.configLeftRightCruiseVelocity(velocityTarget,velocityTarget);
                Robot.drive.setLeftRight(distance,distance);
                break;
        }
    }

    @Override
    protected boolean isFinished()
    {
            if (Timer.getFPGATimestamp() - start >= timeout) {
                return true;
            } else if (allowOvershoot) {
                return Math.abs(Robot.drive.getEncoderPos()) >  Math.abs(distance);
            }
            return Math.abs(distance - Robot.drive.getEncoderPos()) <= Robot.drive.inchesToEncoderCounts(1.5);
    }

    @Override
    public void end()
    {
        super.end();
        Robot.drive.disableOutput();
        isInit = false;
        timeout = Double.POSITIVE_INFINITY;
    }

}
