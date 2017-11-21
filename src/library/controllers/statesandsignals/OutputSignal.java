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
	
	public void setMotor(double power) {
		this.put("motor", power);
	}
	
	public double getMotor() {
		return this.get("motor");
	}
	
	public void setLeftMotor(double power) {
		this.put("leftMotor", power);
	}
	
	public double getLeftMotor() {
		return this.get("leftMotor");
	}
	
	public void setRightMotor(double power) {
		this.put("rightMotor", power);
	}
	
	public double getRightMotor() {
		return this.get("rightMotor");
	}
	
	public void setLeftRightMotor(double left, double right) {
		this.put("leftMotor", left);
		this.put("rightMotor", right);
	}
	
}
