package lib.controllers.drive.equations;

import lib.controllers.Controller;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;

/*
 * @author Chase Blagden <p> Basic format of the arcade drive equation
 */
public class DriveArcadeEquation extends Controller
{

	@Override
	public OutputSignal getOutputSignal(InputState input)
	{
		OutputSignal signal = new OutputSignal();
		double throttle = input.getY();
		double turn = input.getX();
		signal.setLeftRightMotor(throttle + turn, throttle - turn);
		return signal;
	}

	@Override
	public boolean isCompleted()
	{
		return false;
	}

}
