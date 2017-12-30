package org.usfirst.frc.team3309.commands;

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
	 * Controller does processing of data for ControlledCommand.
	 * */
	protected Controller mController;
	
	protected void setController(Controller controller) {
		mController = controller;
	}
	
	protected Controller getController() {
		return mController;
	}
	
	/*
	 * <p>Template for setting input into Controller.
	 * 
	 * */
	protected abstract InputState getInputState();
	
	/*
	 * <p>A ControlledCommand will only be complete when the controller is.
	 * */
	@Override
	public boolean isFinished() {
		if (mController != null) {
			return mController.isCompleted();
		}
		return false;
	}
	
	public void sendToDashboard() {
		if (mController != null) {
			mController.sendToSmartDash();
		}
	}
	
}
