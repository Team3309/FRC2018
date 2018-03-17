package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveStraightProperly extends Command
{

    private boolean isInit = false;

    public enum DriveStrategy {
        VELOCITY,
        MOTION_MAGIC,
        POSITION,
    }

    private DriveStrategy strategy;
    private double distance;
    private int velocityTarget = 15000;
    private boolean isOvershoot = false;

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

    public DriveStraightProperly(double distance, int velocity, boolean isOvershoot) {
        this(distance, velocity);
        this.isOvershoot = isOvershoot;
    }

    @Override
    protected void initialize() {
        super.initialize();
        Robot.drive.reset();
        isInit = true;
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
                if(distance > Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos()))
                    Robot.drive.setLeftRight(velocityTarget,velocityTarget);
                else
                    Robot.drive.setLeftRight(-velocityTarget,-velocityTarget);
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
        return Math.abs(distance - Robot.drive.getEncoderPos()) <= Robot.drive.inchesToEncoderCounts(1.5) ? !isOvershoot :
                Math.abs(distance) > Math.abs(Robot.drive.getEncoderPos());
    }

    @Override
    protected void end()
    {
        super.end();
        Robot.drive.disableOutput();
        isInit = false;
    }

}
