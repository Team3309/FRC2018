package org.usfirst.frc.team3309.commands.autos;

import lib.controllers.drive.DrivePositionController;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.ControlledCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveForwardAuto extends ControlledCommand{

    public DriveForwardAuto() {
        requires(Robot.drive);
    }

    @Override
    protected void initialize() {
        this.setController(new DrivePositionController(30));
    }

    @Override
    protected void execute() {
        this.sendToDashboard();
        this.setController(new DrivePositionController(50));
        Robot.drive.setLeftRight(getSignal().getLeftMotor(), getSignal().getRightMotor());
    }

    @Override
    protected InputState getInputState() {
        InputState input = new InputState();
        input.setAngPos(Robot.drive.getAngPos());
        input.setPos(Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos()));
        return input;
    }
}
