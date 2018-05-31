package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.controllers.drive.DriveCheezyController;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveSignal;
import org.usfirst.frc.team3309.lib.math.LibMath;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveTeleop extends Command {

    private DriveCheezyController cheezyDriveEquation;

    private final double MIN_TRIGGER_POWER = 0.1;

    public DriveTeleop() {
        requires(Robot.drive);
        cheezyDriveEquation = new DriveCheezyController();
    }

    @Override
    protected void initialize() {
        Robot.drive.setHighGear();
        Robot.drive.changeToBrakeMode();
        Robot.drive.changeToPercentMode();
    }

    @Override
    protected void execute() {

        DriveSignal driveSignal;
        boolean isQuickTurn;

        double throttle = -LibMath.handleDeadband(OI.driverRemoteLeft.getY(), 0.04);
        double turn = -LibMath.handleDeadband(OI.driverRemoteRight.getX(), 0.02);

        isQuickTurn = OI.driverRemoteRight.getTrigger();

        driveSignal = cheezyDriveEquation.update(throttle, turn, isQuickTurn);
        Robot.drive.setLeftRight(driveSignal.getLeftMotor(), driveSignal.getRightMotor());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
