package library.controllers;

import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

public class BlankController extends Controller {

	// power for motor
	private double power = 0.0;

	@Override
	public OutputSignal getOutputSignal(InputState input) {
		OutputSignal output = new OutputSignal();
		output.setMotor(power);
		output.setLeftRightMotor(0, 0);
		return output;
	}

	/*
	 * no goal, so always true
	 */
	@Override
	public boolean isCompletable() {
		return true;
	}

	/*
	 * stops motor
	 */
	@Override
	public void reset() {
		power = 0;
	}

	/*
	 * nothing to send
	 */
	@Override
	public void sendToSmartDash() {

	}

	/*
	 * @return
	 * 
	 * @param motor power
	 */
	public void setPower(double power) {
		this.power = power;
	}

	/*
	 * @return motor power
	 * 
	 * @param
	 */
	public double getPower() {
		return power;
	}

}
