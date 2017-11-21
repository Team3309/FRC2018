package library.controllers.pid;

import library.controllers.Controller;
import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

public class BlankController extends Controller {

	// power for motor
	private double power;

	@Override
	public OutputSignal getOutputSignal(InputState input) {
		OutputSignal output = new OutputSignal();
		output.setMotor(power);
		return null;
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
