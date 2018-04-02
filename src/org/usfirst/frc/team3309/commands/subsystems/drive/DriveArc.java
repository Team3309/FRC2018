package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team3309.lib.CommandEx;
import org.usfirst.frc.team3309.lib.controllers.drive.ArcController;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveSignal;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveState;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Constants;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveArc extends CommandEx {

    private ArcController arcController;
    private Timer timer = new Timer();
    private boolean isInitialized = false;
    private boolean backwards;
    private boolean allowOvershoot;
    private double angleDegrees;
    private boolean isAbs = false;

    public DriveArc(Length radius, double angleDegrees, double vel, boolean backwards, boolean allowOvershoot) {
        requires(Robot.drive);
        this.angleDegrees = angleDegrees;
        this.allowOvershoot = allowOvershoot;
        this.backwards = backwards;
        arcController = new ArcController(Robot.drive.inchesToEncoderCounts(radius.toInches()), Math.toRadians(angleDegrees),
                Robot.drive.inchesToEncoderCounts(Constants.WHEELBASE_INCHES.toInches()), vel);
    }

    public DriveArc(Length radius, double angleDegrees, double vel, boolean backwards) {
        this(radius, angleDegrees, vel, backwards, false);
    }

    public DriveArc(Length radius, double angleDegrees, double vel, boolean backwards, boolean allowOvershoot, boolean isAbs) {
        this(radius, angleDegrees, vel, backwards, allowOvershoot);
        this.isAbs = isAbs;
    }

    @Override
    public void initialize() {
        super.initialize();
        timer.start();
        Robot.drive.reset();
        Robot.drive.setHighGear();
        Robot.drive.changeToBrakeMode();
        Robot.drive.changeToVelocityMode();
        System.out.println("Goal angle ------ " + Math.toDegrees(arcController.getGoalAngle()));
        isInitialized = true;
    }

    @Override
    protected void execute() {
        if (!isInitialized) {
            initialize();
        }
        DriveState driveState = new DriveState(Robot.drive.getEncoderPos(), Math.toRadians(Robot.drive.getAngPos()));
        DriveSignal driveSignal = arcController.update(driveState);
        System.out.println("Drive angle " + Robot.drive.getAngPos());
        if (backwards) {
            Robot.drive.setLeftRight(-driveSignal.getLeftMotor(), -driveSignal.getRightMotor());
        } else {
            Robot.drive.setLeftRight(driveSignal.getLeftMotor(), driveSignal.getRightMotor());
        }
    }

    @Override
    protected boolean isFinished() {
        if (isAbs) {
            return Math.abs(Robot.drive.getAngPos()) > Math.abs(Robot.drive.getAngPos() + angleDegrees);
        }
        return allowOvershoot ? Math.abs(Robot.drive.getAngPos()) > Math.abs(angleDegrees) : arcController.isFinished();
    }

    @Override
    public void end() {
        timer.reset();
        super.end();
        isInitialized = false;
        timer.reset();
    }

}
