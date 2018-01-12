package lib.controllers;

import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;

public class BlankController extends Controller {

	private double power = 0.0;

	@Override
	public OutputSignal getOutputSignal(InputState input) {
		OutputSignal output = new OutputSignal();
		output.setMotor(power);
		output.setLeftRightMotor(0, 0);
		return output;
	}

	/*
	 * <p>no goal, so always true
	 */
	@Override
	public boolean isCompleted() {
		return true;
	}

	@Override
	public void reset() {
		power = 0;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public double getPower() {
		return power;
	}

}
