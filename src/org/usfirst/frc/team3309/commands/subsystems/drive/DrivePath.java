package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.controllers.drive.BiArcController;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveSignal;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveState;
import org.usfirst.frc.team3309.lib.controllers.helpers.Waypoint;
import org.usfirst.frc.team3309.robot.Constants;
import org.usfirst.frc.team3309.robot.Robot;

import java.util.ArrayList;

public class DrivePath extends Command {

    private BiArcController biArcController;

    public DrivePath(ArrayList<Waypoint> path) {
        requires(Robot.drive);
        biArcController = new BiArcController(path, Constants.WHEELBASE_INCHES);
    }

    @Override
    protected void initialize() {
        Robot.drive.setLowGear();
        Robot.drive.changeToPercentMode();
    }

    @Override
    protected void execute() {
        DriveState driveState = new DriveState(Robot.drive.inchesToEncoderCounts(Robot.drive.getEncoderPos()),
                Robot.drive.getAngPos());
        DriveSignal driveSignal = biArcController.update(driveState);
        Robot.drive.setLeftRight(Robot.drive.inchesToEncoderCounts(driveSignal.getLeftMotor()),
                Robot.drive.inchesToEncoderCounts(driveSignal.getRightMotor()));
    }

    @Override
    protected boolean isFinished() {
        return biArcController.isFinished();
    }

}
