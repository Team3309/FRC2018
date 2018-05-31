package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.lib.CommandEx;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDController;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveStraight extends CommandEx {

    private boolean isInit = false;
    private double startAngle = Double.NaN;
    private double start;
    private double timeout = Double.POSITIVE_INFINITY;
    private PIDController angleController;
    private boolean isPigeon = false;
    private boolean isChecked = false;

    public enum DriveStrategy {
        VELOCITY,
        MOTION_MAGIC,
        POSITION,
    }

    private DriveStrategy strategy;
    private double distance;
    private int velocityTarget = 15000;
    private boolean allowOvershoot = false;

    public DriveStraight(double distance, DriveStrategy strategy) {
        this.strategy = strategy;
        this.distance = Robot.drive.inchesToEncoderCounts(distance);
        requires(Robot.drive);
    }

    public DriveStraight(double distance, int velocity) {
        this(distance, DriveStrategy.VELOCITY);
        this.velocityTarget = velocity;
    }

    public DriveStraight(double distance, int velocity, boolean allowOvershoot, boolean isPigeon) {
        this(distance, velocity, allowOvershoot);
        this.isPigeon = isPigeon;
    }

    public DriveStraight(double distance, int velocity, boolean allowOvershoot) {
        this(distance, velocity);
        this.allowOvershoot = allowOvershoot;
    }

    public DriveStraight(double distance, int velocity, double angle) {
        this(distance, velocity, true, true);
        this.startAngle = angle;
    }

    public DriveStraight(double distance, int velocity, boolean allowOvershoot, double timeout) {
        this(distance, velocity, allowOvershoot);
        this.timeout = timeout;
    }

    @Override
    public void initialize() {
        super.initialize();
        Robot.drive.reset();
        isInit = true;
        start = Timer.getFPGATimestamp();
        if (isPigeon) {
            angleController = new PIDController(new PIDConstants(0.038, 0.0, 0.01)); // 0.038, 0.0, 0.01
            if (Double.isNaN(startAngle)) {
                startAngle = Robot.drive.getPigeonPos();
            }
        } else {
            angleController = new PIDController(new PIDConstants(0, 0, 0));
            if (Double.isNaN(startAngle)){
                startAngle = Robot.drive.getAngPos();
            }
        }

    }

    @Override
    protected void execute() {
        if (!isInit) {
            initialize();
        }
        SmartDashboard.putNumber("error: ", Math.abs(distance - Robot.drive.getEncoderPos()));
        switch (strategy) {
            case POSITION:
                Robot.drive.changeToPositionMode();
                Robot.drive.setLeftRight(distance, distance);
                break;
            case VELOCITY:
                Robot.drive.changeToVelocityMode();
                double vel = isPigeon ? 30000 : -30000;
                double angularUpdate = vel * angleController.update(isPigeon ? Robot.drive.getPigeonPos() : Robot.drive.getAngVel(), startAngle);
                if (distance > Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos()))
                    Robot.drive.setLeftRight(velocityTarget + angularUpdate, velocityTarget - angularUpdate);
                else
                    Robot.drive.setLeftRight(-velocityTarget + angularUpdate, -velocityTarget - angularUpdate);
                break;
            case MOTION_MAGIC:
                Robot.drive.changeToMotionMagicMode();
                Robot.drive.configLeftRightCruiseVelocity(velocityTarget, velocityTarget);
                Robot.drive.setLeftRight(distance, distance);
                break;
        }
    }

    @Override
    protected boolean isFinished() {
        if (!isChecked) {
            isChecked = true;
            return false;
        } else {
            if (Timer.getFPGATimestamp() - start >= timeout) {
                return true;
            } else if (allowOvershoot) {
                return Math.abs(Robot.drive.getEncoderPos()) > Math.abs(distance);
            }
            return Math.abs(distance - Robot.drive.getEncoderPos()) <= Robot.drive.inchesToEncoderCounts(1.5);
        }
    }

    @Override
    public void end() {
        super.end();
        Robot.drive.disableOutput();
        System.out.println("Drive dist " + Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos()));
        isInit = false;
        timeout = Double.POSITIVE_INFINITY;
    }

}
