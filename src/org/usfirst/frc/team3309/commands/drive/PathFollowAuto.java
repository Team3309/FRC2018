package org.usfirst.frc.team3309.commands.drive;

import lib.controllers.BlankController;
import lib.controllers.drive.PurePursuitController;
import lib.controllers.drive.Waypoint;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;

import org.usfirst.frc.team3309.commands.ControlledCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class PathFollowAuto extends ControlledCommand {

	private Waypoint[] path  = {
			new Waypoint(3, 10, 40),
			new Waypoint(6, 14, 90)
	};

	public PathFollowAuto() {
		requires(Robot.drive);
	}

	@Override
	protected void execute() {
		this.setController(new PurePursuitController(path));
		OutputSignal signal = getController().getOutputSignal(getInputState());
		Robot.drive.setLeftRight(signal.getLeftMotor(), signal.getRightMotor());
	}

	@Override
	protected InputState getInputState() {
		InputState inputState = new InputState();
		inputState.setPos(Robot.drive.encoderCountsToInches(Robot.drive
				.getAverageEncoder()));
		inputState.setAngPos(Robot.drive.getAngPos());
		return inputState;
	}
	
	@Override
	public void cancel() {
		this.setController(new BlankController());
	}

}
