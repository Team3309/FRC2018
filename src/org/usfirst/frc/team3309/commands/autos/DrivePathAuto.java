package org.usfirst.frc.team3309.commands.autos;

import lib.controllers.drive.PurePursuitController;
import lib.controllers.drive.Waypoint;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;
import org.usfirst.frc.team3309.commands.ControlledCommand;
import org.usfirst.frc.team3309.robot.Robot;
import org.usfirst.frc.team3309.robot.RobotMap;

public class DrivePathAuto extends ControlledCommand {

    public DrivePathAuto() {
        requires(Robot.drive);
    }

    private final Waypoint[] path = {
            new Waypoint(0, 0, 0),
            new Waypoint(40, -40,40)
    };
    private PurePursuitController purePursuitController;

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
        OutputSignal signal = getController().getOutputSignal(getInputState());
        Robot.drive.setLeftRight(signal.getLeftMotor(), signal.getRightMotor());
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
