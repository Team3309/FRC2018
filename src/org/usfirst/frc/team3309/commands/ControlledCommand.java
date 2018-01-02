package org.usfirst.frc.team3309.commands;

import lib.controllers.BlankController;
import lib.controllers.Controller;
import lib.controllers.statesandsignals.InputState;
import edu.wpi.first.wpilibj.command.Command;

/*
 * @author Chase Blagden
 * 
 * <p>A Command of wpilib extended to include controller and inputs.
 * 
 * */
public abstract class ControlledCommand extends Command {

	public ControlledCommand() {
	}

	public ControlledCommand(String commandName) {
		super(commandName);
	}

	/*
	 * <p> Controller does processing of data for ControlledCommand.
	 * 
	 * <p> Set to BlankController to ensure motors won't move if called
	 * prematurely.
	 */
	protected Controller mController = new BlankController();

	protected void setController(Controller controller) {
		mController = controller;
	}

	protected Controller getController() {
		return mController;
	}

	/*
	 * <p>Template for setting input into Controller.
	 */
	protected abstract InputState getInputState();

	/*
	 * <p>A ControlledCommand will only be complete when the controller is.
	 */
	@Override
	public boolean isFinished() {
		return mController.isCompleted();
	}

	public void sendToDashboard() {
		mController.sendToSmartDash();
	}

}
