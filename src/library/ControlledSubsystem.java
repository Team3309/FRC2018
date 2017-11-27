package library;

import library.controllers.Controller;
import library.controllers.statesandsignals.InputState;

/*
 * outline for subsystem with controller
 * @author Chase Blagden
 * */
public abstract class ControlledSubsystem extends Subsystem {

	/*
	 * Controller for subsystem
	 */
	private Controller mController;
	
	/*
	 * input to controller
	 * */
	public abstract InputState getInputState();

	/*
	 * @return
	 * 
	 * @param subsystemID
	 */
	public ControlledSubsystem(String subsystemID) {
		super(subsystemID);
	}

	/*
	 * @param mController
	 * 
	 * @return
	 */
	public void setController(Controller mController) {
		this.mController = mController;
	}

	/*
	 * @param
	 * 
	 * @return Controller
	 */
	public Controller getController() {
		return mController;
	}

}
