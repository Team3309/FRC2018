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
        for (Waypoint waypoint : path) {
            waypoint.setRadius(Robot.drive.inchesToEncoderCounts(waypoint.getRadius()));
        }
        biArcController = new BiArcController(path, Robot.drive.inchesToEncoderCounts(Constants.WHEELBASE_INCHES));
    }

    @Override
    protected void initialize() {
        Robot.drive.setLowGear();
        Robot.drive.enableBrakeMode(false);
        Robot.drive.changeToPercentMode();
    }

    @Override
    protected void execute() {
        DriveState driveState = new DriveState(Robot.drive.getEncoderPos(),
                Robot.drive.getAngPos());
        DriveSignal driveSignal = biArcController.update(driveState);
        Robot.drive.setLeftRight(driveSignal.getLeftMotor(),
                driveSignal.getRightMotor());
    }

    @Override
    protected boolean isFinished() {
        return biArcController.isFinished();
    }

}
