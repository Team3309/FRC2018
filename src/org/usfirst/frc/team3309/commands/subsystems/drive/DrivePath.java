package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import lib.controllers.drive.DriveSignal;
import lib.controllers.drive.PurePursuitController;
import lib.controllers.drive.Waypoint;
import org.usfirst.frc.team3309.robot.Robot;
import org.usfirst.frc.team3309.robot.RobotMap;

import java.util.ArrayList;

public class DrivePath extends Command {

    private PurePursuitController purePursuitController;

    public DrivePath(ArrayList<Waypoint> path) {
        requires(Robot.drive);
        purePursuitController = new PurePursuitController(path, RobotMap.WHEELBASE_INCHES);
    }

    @Override
    protected void initialize() {
        Robot.drive.setLowGear();
        Robot.drive.setVoltageRampRate(10);
        Robot.drive.changeToVelocityMode();
    }

    @Override
    protected void execute() {
        DriveSignal driveSignal = purePursuitController.update(Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos()),
                Robot.drive.getAngPos());
        Robot.drive.setLeftRight(driveSignal.getLeftMotor(), driveSignal.getRightMotor());
    }

    @Override
    protected boolean isFinished() {
        return purePursuitController.isFinished();
    }

}
