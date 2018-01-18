package org.usfirst.frc.team3309.commands.drive;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import lib.controllers.drive.DriveSignal;
import lib.controllers.drive.equations.DriveCheezyDriveEquation;
import org.usfirst.frc.team3309.driverstation.Controls;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveTeleop extends Command {

    private DriveCheezyDriveEquation cheezyDriveEquation;

    public DriveTeleop() {
        cheezyDriveEquation = new DriveCheezyDriveEquation();
        requires(Robot.drive);
    }

    @Override
    protected void initialize() {
        Robot.drive.changeToPercentMode();
        Robot.drive.setHighGear();
    }

    @Override
    protected void execute() {
        double throttle = Controls.driverRemote.getY(Hand.kLeft);
        double turn = Controls.driverRemote.getX(Hand.kRight);
        boolean isQuickTurn = Controls.driverRemote.getBumper(Hand.kLeft);
        DriveSignal driveSignal = cheezyDriveEquation.update(throttle, turn, isQuickTurn);
        Robot.drive.setLeftRight(driveSignal.getLeftMotor(), driveSignal.getRightMotor());
        if (Controls.driverRemote.getBumper(Hand.kLeft)) {
            Robot.drive.setLowGear();
        } else {
            Robot.drive.setHighGear();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
