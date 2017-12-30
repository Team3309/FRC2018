package library.controllers.drive.equations;

import library.controllers.Controller;
import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

/*
 * @author Chase Blagden
 * <p> Basic format of the arcade drive equation
 * */
public class DriveArcadeEquation extends Controller {

	@Override
	public OutputSignal getOutputSignal(InputState input) {
		OutputSignal signal = new OutputSignal();
		double throttle = input.getY();
		double turn = input.getX();
		signal.setLeftRightMotor(throttle + turn, throttle - turn);
		return signal;
	}

	@Override
	public boolean isCompleted() {
		return false;
	}

}
