package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    private double timeout = Double.NaN;
    private double start;

    public DriveArc(Length radius, double angleDegrees, double vel, boolean backwards, boolean allowOvershoot) {
        requires(Robot.drive);
        this.angleDegrees = angleDegrees;
        this.allowOvershoot = allowOvershoot;
        this.backwards = backwards;
        arcController = new ArcController(Robot.drive.inchesToEncoderCounts(radius.toInches()), Math.toRadians(angleDegrees),
                Robot.drive.inchesToEncoderCounts(Constants.WHEELBASE_INCHES.toInches()), vel);
        SmartDashboard.putNumber("left arc error", 0);
        SmartDashboard.putNumber("right arc error", 0);
    }

    public DriveArc(Length radius, double angleDegrees, double vel, boolean backwards) {
        this(radius, angleDegrees, vel, backwards, false);
    }

    public DriveArc(Length radius, double angleDegrees, double vel, boolean backwards, boolean allowOvershoot, boolean isAbs) {
        this(radius, angleDegrees, vel, backwards, allowOvershoot);
        this.isAbs = isAbs;
    }

    public DriveArc(Length radius, double angleDegrees, double vel, boolean backwards, double timeout) {
        this(radius, angleDegrees, vel, backwards, true);
        this.timeout = timeout;
    }

    @Override
    public void initialize() {
        super.initialize();
        start = Timer.getFPGATimestamp();
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
        SmartDashboard.putNumber("left arc error", arcController.getLeftError());
        SmartDashboard.putNumber("right arc error", arcController.getRightError());
        if (backwards) {
            Robot.drive.setLeftRight(-driveSignal.getLeftMotor(), -driveSignal.getRightMotor());
        } else {
            Robot.drive.setLeftRight(driveSignal.getLeftMotor(), driveSignal.getRightMotor());
        }
    }

    @Override
    protected boolean isFinished() {

        // timeout check
   /*     double now = Timer.getFPGATimestamp();
        if (timeout != Double.NaN && now - start > timeout) {
            System.out.println("Arc timed out!!!!");
            return true;
        }*/

        // absolute mode
        if (isAbs) {
            return timer.get() > 0.6 && Math.abs(Robot.drive.getAngPos()) > Math.abs(Robot.drive.getAngPos() + angleDegrees);
        }

        // relative mode
        return timer.get() > 0.6 && allowOvershoot ? Math.abs(Robot.drive.getAngPos()) > Math.abs(angleDegrees) : arcController.isFinished();
    }

    @Override
    public void end() {
        timer.reset();
        super.end();
        isInitialized = false;
        Robot.drive.disableOutput();

    }

}
