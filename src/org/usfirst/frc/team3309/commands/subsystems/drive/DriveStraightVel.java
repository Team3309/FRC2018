package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.lib.CommandEx;
import org.usfirst.frc.team3309.lib.LibTimer;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveStraightVel extends CommandEx {

    private boolean isInit;
    private double vel;
    private double error;
    private final double errorThreshold = 2;
    private double pos;
    private LibTimer timer = new LibTimer(0.2);
    private double timeoutSec;
    private double start = Double.POSITIVE_INFINITY;

    public DriveStraightVel(double pos, double vel, double timeoutSec) {
        requires(Robot.drive);
        this.vel = Math.abs(vel);
        this.timeoutSec = timeoutSec;
        this.pos = pos;
    }

    @Override
    public void initialize() {
        super.initialize();
        start = Timer.getFPGATimestamp();
        Robot.drive.reset();
        Robot.drive.changeToVelocityMode();
        Robot.drive.changeToBrakeMode();
        Robot.drive.setGoalPos(pos);
        if (pos < 0 ) {
            vel *= -1;
        }
        isInit = true;
    }

    @Override
    public void execute() {
        if (!isInit) {
            initialize();
        }
        double now = Timer.getFPGATimestamp();
        if (now - start < 0.15) {
            System.out.println("watintg...");
        }
        error = Math.abs(Robot.drive.getGoalPos()) - Math.abs(Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos()));
        System.out.println("Goal pos " + Robot.drive.getGoalPos());
        System.out.println("Pos " + Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos()));
        SmartDashboard.putNumber("DriveStraightVel error", error);
        Robot.drive.setLeftRight(vel, vel);
    }

    @Override
    protected boolean isFinished() {
        double now = Timer.getFPGATimestamp();
        if (now - start < 0.15) {
            return false;
        }
        if (Math.signum(error) != Math.signum(Robot.drive.getGoalPos())) {
            System.out.println("Threshold reached");
            return true;
        } else if (now - start > timeoutSec) {
            System.out.println("Timer done");
            return true;
        }
        return false;
    }

    @Override
    public void end() {
        super.end();
        isInit = false;
        start = Double.POSITIVE_INFINITY;
    }

}
