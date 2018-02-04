package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.controllers.drive.DriveAngleController;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveSignal;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveTurn extends Command {

    private double goalAngle;
    private DriveAngleController angleController;

    public DriveTurn(double goalAngle) {
        this.goalAngle = goalAngle;
        angleController = new DriveAngleController(new PIDConstants(0, 0, 0));
        requires(Robot.drive);
    }

    @Override
    protected void initialize() {
        Robot.drive.changeToPercentMode();
    }

    @Override
    protected void execute() {
        DriveSignal driveSignal = angleController.update(Robot.drive.getAngPos(), goalAngle);
        Robot.drive.setLeftRight(driveSignal.getLeftMotor(), driveSignal.getRightMotor());
    }

    @Override
    protected boolean isFinished() {
        return angleController.isFinished();
    }
}
