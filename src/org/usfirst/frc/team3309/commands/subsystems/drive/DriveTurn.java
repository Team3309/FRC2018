package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.lib.CommandEx;
import org.usfirst.frc.team3309.lib.LibTimer;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDController;
import org.usfirst.frc.team3309.lib.math.LibMath;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveTurn extends CommandEx {

    private double goalAngle;
    private PIDController angleController;
    private final double ANGLE_LENIENCY = 10;
    private boolean isInitialized = false;
    private LibTimer timer = new LibTimer(0.15);
    private double timeoutSec = Double.POSITIVE_INFINITY;
    private boolean isPigeon = false;
    private double start = Double.POSITIVE_INFINITY;

    public DriveTurn(double goalAngle) {
        this.goalAngle = goalAngle;
        requires(Robot.drive);
    }

    public DriveTurn(double goalAngle, double timeoutSec) {
        this(goalAngle);
        this.timeoutSec = timeoutSec;
        SmartDashboard.putNumber("kP", 0);
        SmartDashboard.putNumber("kI", 0);
        SmartDashboard.putNumber("kD", 0);
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
        Robot.drive.changeToVelocityMode();
        timer.start();
        start = Timer.getFPGATimestamp();
    }

    @Override
    protected void execute() {
        if (!isInitialized) {
            initialize();
        }
        System.out.println("goalAngle"+goalAngle);
        System.out.println("pigeion" + Robot.drive.getPigeonPos());
        double kP = SmartDashboard.getNumber("kP", 0.0735);
        double kI = SmartDashboard.getNumber("kI", 0.0); // 0.0014
        double kD = SmartDashboard.getNumber("kD", 0.0); // 0.068
        kP = 0.0782;
        kI = 0.00039;
        kD = 0.071;
        angleController = new PIDController(new PIDConstants(kP, kI, kD));
        double power = 30000 * angleController.update(isPigeon ? Robot.drive.getPigeonPos() : Robot.drive.getAngVel(), goalAngle);
        Robot.drive.setLeftRight(power, -power);
        System.out.println(power);
    }

    @Override
    protected boolean isFinished() {
        return
                timer.isConditionMaintained(
                        LibMath.isWithin(isPigeon ? Robot.drive.getPigeonPos() : Robot.drive.getAngPos(),
                                goalAngle - ANGLE_LENIENCY, goalAngle + ANGLE_LENIENCY))
                        || timer.get() > timeoutSec || (Timer.getFPGATimestamp() - start) > timeoutSec;
    }

    @Override
    public void end() {
        super.end();
        timer.reset();
        start = Double.POSITIVE_INFINITY;
        isInitialized = false;
        timeoutSec = Double.POSITIVE_INFINITY;
    }

}

