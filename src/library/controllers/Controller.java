package library.controllers;

import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

/*
 * generic setup for system controller
 * @author Chase Blagden
 * */
public abstract class Controller {
	
	// name of Controller
	private String name = "Default Controller Name";
	
	// subsystem to which controller is assigned
	private String subsystemID = "Default Subsystem";
	
	/*
	 * @return boolean indicating whether controller has finished processing
	 * */
	public abstract boolean isFinished();

	/*
	 * @return OuputSignal - power to be returned after processing
	 * */
	public abstract OutputSignal getOutputSignal(InputState input);
	
	/*
	 * for reseting controller to default values
	 * */
	public abstract void reset();
	
	
	public abstract void sendToSmartDash();
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSubsystemID(String id) {
		this.subsystemID = id;
	}
	
	public String getSubsystemID() {
		return this.subsystemID;
	}
	
}
