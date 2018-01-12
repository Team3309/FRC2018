package lib.controllers;

import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;

/*
 * @author Chase Blagden
 * <p>generic setup for system controller
 * */
public abstract class Controller {

	private String name = "Default Controller Name";

	/*
	 * @return OutputSignal - power to be returned after processing
	 */
	public abstract OutputSignal getOutputSignal(InputState inputState);

	public void sendToDashboard() {
	}

	/*
	 * @return for resetting controller to default values
	 */
	public void reset() {
	}

	/*
	 * @return boolean indicating whether controller has finished processing
	 */
	public abstract boolean isCompleted();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


}
