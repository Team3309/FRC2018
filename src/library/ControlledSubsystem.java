package library;

import library.controllers.Controller;
import library.controllers.statesandsignals.InputState;

/*
 * @author Chase Blagden
 * <p>outline for subsystem with controller
 * */
public abstract class ControlledSubsystem extends Subsystem {

	/*
	 * <p>Controller for subsystem
	 */
	private Controller mController;

	/*
	 * <p>input to controller
	 */
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
