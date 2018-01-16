package org.usfirst.frc.team3309.commands.autos;

import lib.controllers.drive.PurePursuitController;
import lib.controllers.drive.Waypoint;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.ControlledCommand;
import org.usfirst.frc.team3309.robot.Robot;
import org.usfirst.frc.team3309.robot.RobotMap;

import java.util.ArrayList;

public class DrivePathAuto extends ControlledCommand {

    private PurePursuitController purePursuitController;

    private ArrayList<Waypoint> path;

    public DrivePathAuto(ArrayList<Waypoint> path) {
        requires(Robot.drive);
        this.path = path;
    }

    @Override
    protected void initialize() {
        purePursuitController = new PurePursuitController(path, RobotMap.WHEELBASE_INCHES);
        this.setController(purePursuitController);
        Robot.drive.setLowGear();
        Robot.drive.setVoltageRampRate(10);
        Robot.drive.changeToVelocityMode();
    }

    @Override
    protected void execute() {
        this.sendToDashboard();
        this.setController(purePursuitController);
        Robot.drive.setLeftRight(getSignal().getLeftMotor(), getSignal().getRightMotor());
    }

    @Override
    protected InputState getInputState() {
        InputState input = new InputState();
        input.setPos(Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos()));
        input.setAngPos(Robot.drive.getAngPos());
        input.setAngVel(Robot.drive.getAngVel());
        input.setVel(Robot.drive.encoderCountsToInches(Robot.drive.getEncoderVelocity()));
        return input;
    }

}
