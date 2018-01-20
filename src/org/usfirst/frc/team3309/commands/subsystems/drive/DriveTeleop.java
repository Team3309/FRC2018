package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.driverstation.Controls;
import org.usfirst.frc.team3309.lib.controllers.drive.DriveSignal;
import org.usfirst.frc.team3309.lib.controllers.drive.equations.DriveCheezyDriveEquation;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveTeleop extends Command {

    private DriveCheezyDriveEquation cheezyDriveEquation;

    public DriveTeleop() {
        requires(Robot.drive);
        cheezyDriveEquation = new DriveCheezyDriveEquation();
    }

    @Override
    protected void initialize() {
        Robot.drive.changeToPercentMode();
        Robot.drive.setHighGear();
    }

    @Override
    protected void execute() {
        double throttle = Controls.driverRemote.leftStick.getY();
        double turn = -Controls.driverRemote.rightStick.getX();
        SmartDashboard.putNumber("Turn: ",turn);
        boolean isQuickTurn = Controls.driverRemote.rightBumper.get();
        DriveSignal driveSignal = cheezyDriveEquation.update(throttle, turn, isQuickTurn);
        Robot.drive.setLeftRight(driveSignal.getLeftMotor(), driveSignal.getRightMotor());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
