package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.lib.CommandEx;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDController;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveStraightProperly extends CommandEx
{

    private boolean isInit = false;
    private double startAngleVel;
    private PIDController angleController = new PIDController(new PIDConstants(0.01, 0, 0));

    public enum DriveStrategy {
        VELOCITY,
        MOTION_MAGIC,
        POSITION,
    }

    private DriveStrategy strategy;
    private double distance;
    private int velocityTarget = 15000;
    private boolean allowOvershoot = false;

    public DriveStraightProperly(double distance, DriveStrategy strategy)
    {
        this.strategy = strategy;
        this.distance = Robot.drive.inchesToEncoderCounts(distance);
        requires(Robot.drive);
    }

    public DriveStraightProperly(double distance, int velocity) {
        this(distance, DriveStrategy.VELOCITY);
        this.velocityTarget = velocity;
    }

    public DriveStraightProperly(double distance, int velocity, boolean allowOvershoot) {
        this(distance, velocity);
        this.allowOvershoot = allowOvershoot;
    }

    @Override
    public void initialize() {
        super.initialize();
        Robot.drive.reset();
        isInit = true;
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
                Robot.drive.setLeftRight(distance,distance);
                break;
            case VELOCITY:
                Robot.drive.changeToVelocityMode();
                double angularUpdate = 30000 * angleController.update(Robot.drive.getAngVel(), startAngleVel);
                if(distance > Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos()))
                    Robot.drive.setLeftRight(velocityTarget + angularUpdate,velocityTarget - angularUpdate);
                else
                    Robot.drive.setLeftRight(-velocityTarget + angularUpdate,-velocityTarget - angularUpdate);
                break;
            case MOTION_MAGIC:
                Robot.drive.changeToMotionMagicMode();
                Robot.drive.configLeftRightCruiseVelocity(velocityTarget,velocityTarget);
                Robot.drive.setLeftRight(distance,distance);
        }
    }

    @Override
    protected boolean isFinished()
    {
        if (allowOvershoot) {
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
    }

}
