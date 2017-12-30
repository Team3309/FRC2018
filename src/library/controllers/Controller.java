package library.controllers;

import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

/*
 * @author Chase Blagden
 * <p>generic setup for system controller
 * */
public abstract class Controller {

	// name of Controller
	private String name = "Default Controller Name";

	/*
	 * @return OuputSignal - power to be returned after processing
	 */
	public abstract OutputSignal getOutputSignal(InputState input);

	/*
	 * @return for sending values to dash board
	 */
	public void sendToSmartDash() {

	}

	/*
	 * @return for reseting controller to default values
	 */
	public void reset() {
		
	}

	/*
	 * @return boolean indicating whether controller has finished processing
	 */
	public abstract boolean isCompleted();

	/*
	 * @return
	 * 
	 * @param name of controller
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * @return name of controller
	 * 
	 * @param
	 */
	public String getName() {
		return name;
	}

}
