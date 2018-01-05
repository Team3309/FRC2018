package org.usfirst.frc.team3309.commands.autos;

import lib.controllers.drive.PurePursuitController;
import lib.controllers.drive.Waypoint;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;
import org.usfirst.frc.team3309.commands.ControlledCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class DrivePathAuto extends ControlledCommand {

    public DrivePathAuto() {
        requires(Robot.drive);
    }
    private final Waypoint[] path = {
            new Waypoint(-20, 20, -90)
    };

    @Override
    protected void initialize() {
        Robot.drive.setLowGear();
        Robot.drive.setVoltageRampRate(10);
    }

    @Override
    protected void execute() {
        this.sendToDashboard();
        this.setController(new PurePursuitController(path));
        OutputSignal signal = getController().getOutputSignal(getInputState());
        Robot.drive.setLeftRight(signal.getLeftMotor(), signal.getRightMotor());
    }

    @Override
    protected InputState getInputState() {
        InputState input = new InputState();
        input.setPos(Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos()));
        input.setAngPos(Robot.drive.getAngPos());
        return input;
    }
}
