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

    public DriveTeleop() {
        requires(Robot.drive);
        cheezyDriveEquation = new DriveCheezyController();
    }

    @Override
    protected void initialize() {
        Robot.drive.setHighGear();
        Robot.drive.changeToCoastMode();
        Robot.drive.changeToPercentMode();
    }

    @Override
    protected void execute() {
        double throttle = LibMath.handleDeadband(OI.driverRemote.leftStick.getY(), 0.04);
        double turn = LibMath.handleDeadband(OI.driverRemote.rightStick.getX(), 0.02);
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
