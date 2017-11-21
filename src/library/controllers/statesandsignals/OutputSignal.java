package library.controllers.statesandsignals;

import java.util.HashMap;

/*
 * Defines output signal for controllers
 * @author Chase Blagden 
 * */
@SuppressWarnings("serial")
public class OutputSignal extends HashMap<String, Double> {

	
	public OutputSignal() {
		super();
		this.setMotor(0.0);
	}

	/*
	 * @return
	 * @param motor power
	 * */
	public void setMotor(double power) {
		this.put("motor", power);
	}

	/*
	 * @return motor power
	 * @param 
	 * */
	public double getMotor() {
		return this.get("motor");
	}
	
	/*
	 * -------------------------------------------------
	 * -----Methods primarily used for drive train-----
	 * -------------------------------------------------
	 */

	/*
	 * @return
	 * @param left motor power
	 * */
	public void setLeftMotor(double power) {
		this.put("leftMotor", power);
	}
	
	/*
	 * @return left motor power
	 * @param
	 * */
	public double getLeftMotor() {
		return this.get("leftMotor");
	}

	/*
	 * @return
	 * @param right motor power
	 * */
	public void setRightMotor(double power) {
		this.put("rightMotor", power);
	}

	/*
	 * @return right motor power
	 * 
	 * @param
	 */
	public double getRightMotor() {
		return this.get("rightMotor");
	}

	/*
	 * @return
	 * 
	 * @param left power, right power
	 */
	public void setLeftRightMotor(double left, double right) {
		this.put("leftMotor", left);
		this.put("rightMotor", right);
	}
	
	/*
	 * -------------------------------------------------
	 * */

}
