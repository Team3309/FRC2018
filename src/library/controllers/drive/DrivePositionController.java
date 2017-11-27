package library.controllers.drive;

import library.controllers.Controller;
import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

public class DrivePositionController extends Controller {
	
	@Override
	public boolean isCompletable() {
		return false;
	}

	@Override
	public OutputSignal getOutputSignal(InputState input) {
		return null;
	}

	@Override
	public void reset() {
		
	}
	
}
