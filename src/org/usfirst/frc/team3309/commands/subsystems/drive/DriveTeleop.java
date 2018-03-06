package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        boolean isQuickTurn = false;

        double leftTrigger = OI.driverRemote.leftTrigger.getY();
        double rightTrigger = OI.driverRemote.rightTrigger.getY();

        double throttle = 0;
        double turn;

        if (Math.abs(leftTrigger) > MIN_TRIGGER_POWER) {
            turn = leftTrigger;
            isQuickTurn = true;
        } else if (Math.abs(rightTrigger) > MIN_TRIGGER_POWER) {
            turn = -rightTrigger;
            isQuickTurn = true;
        } else {
            throttle = LibMath.handleDeadband(OI.driverRemote.leftStick.getY(), 0.04);
            turn = -LibMath.handleDeadband(OI.driverRemote.rightStick.getX(), 0.02);
            SmartDashboard.putNumber("Turn: ", turn);
            SmartDashboard.putNumber("throttle: ", throttle);
            SmartDashboard.putNumber("turn: ", turn);
        }

        if (OI.driverRemote.rightBumper.get()) {
            Robot.drive.setLeftRight(0.0, 0);
        } else {
            driveSignal = cheezyDriveEquation.update(throttle, turn, isQuickTurn);
            Robot.drive.setLeftRight(driveSignal.getLeftMotor(), driveSignal.getRightMotor());
        }

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
