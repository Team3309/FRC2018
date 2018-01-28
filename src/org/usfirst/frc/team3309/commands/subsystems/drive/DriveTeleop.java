package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveSignal;
import org.usfirst.frc.team3309.lib.controllers.drive.DriveCheezyController;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveTeleop extends Command {

    private DriveCheezyController cheezyDriveEquation;

    public DriveTeleop() {
        requires(Robot.drive);
        cheezyDriveEquation = new DriveCheezyController();
    }

    @Override
    protected void initialize() {
        Robot.drive.changeToPercentMode();
        Robot.drive.setHighGear();
        Robot.drive.enableBrakeMode(false);
    }

    @Override
    protected void execute() {
        double throttle = OI.driverRemote.leftStick.getY();
        double turn = -OI.driverRemote.rightStick.getX();
        SmartDashboard.putNumber("Turn: ",turn);
        boolean isQuickTurn = OI.driverRemote.rightBumper.get();
        DriveSignal driveSignal = cheezyDriveEquation.update(throttle, turn, isQuickTurn);
        Robot.drive.setLeftRight(driveSignal.getLeftMotor(), driveSignal.getRightMotor());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
