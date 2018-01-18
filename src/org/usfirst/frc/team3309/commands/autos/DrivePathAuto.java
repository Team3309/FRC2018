package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.Command;
import lib.controllers.drive.DriveSignal;
import lib.controllers.drive.PurePursuitController;
import lib.controllers.drive.Waypoint;
import org.usfirst.frc.team3309.commands.ControlledCommand;
import org.usfirst.frc.team3309.robot.Robot;
import org.usfirst.frc.team3309.robot.RobotMap;

import java.util.ArrayList;

public class DrivePathAuto extends Command {

    private PurePursuitController purePursuitController;

    private ArrayList<Waypoint> path;

    public DrivePathAuto(ArrayList<Waypoint> path) {
        purePursuitController = new PurePursuitController(path, RobotMap.WHEELBASE_INCHES);
        requires(Robot.drive);
        this.path = path;
    }

    @Override
    protected void initialize() {
        purePursuitController = new PurePursuitController(path, RobotMap.WHEELBASE_INCHES);
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
