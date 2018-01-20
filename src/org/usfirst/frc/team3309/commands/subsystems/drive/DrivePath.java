package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.controllers.drive.DriveSignal;
import org.usfirst.frc.team3309.lib.controllers.drive.DriveState;
import org.usfirst.frc.team3309.lib.controllers.drive.PurePursuitController;
import org.usfirst.frc.team3309.lib.controllers.drive.Waypoint;
import org.usfirst.frc.team3309.robot.Robot;
import org.usfirst.frc.team3309.robot.RobotMap;

import java.util.ArrayList;

public class DrivePath extends Command {

    private PurePursuitController purePursuitController;

    public DrivePath(ArrayList<Waypoint> path) {
        requires(Robot.drive);
        purePursuitController = new PurePursuitController(path, Robot.drive.inchesToEncoderCounts(RobotMap.WHEELBASE_INCHES));
    }

    @Override
    protected void initialize() {
        Robot.drive.setLowGear();
        Robot.drive.changeToPercentMode();
    }

    @Override
    protected void execute() {
        DriveState driveState = new DriveState(Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos()),
                Robot.drive.getAngPos());
        DriveSignal driveSignal = purePursuitController.update(driveState);
        Robot.drive.setLeftRight(driveSignal.getLeftMotor(), driveSignal.getRightMotor());
    }

    @Override
    protected boolean isFinished() {
        return purePursuitController.isFinished();
    }

}
