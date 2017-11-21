package library.controllers.statesandsignals;

import java.util.HashMap;

/*
 * Defines input type for controllers
 * @author Chase Blagden 
 * */
@SuppressWarnings("serial")
public class InputState extends HashMap<String, Double> {
	
	public InputState() {
		super();
		this.setX(0.0);
		this.setY(0.0);
	}
	
	public void setX(double x) {
		this.put("x", x);
	}
	
	public double getX() {
		return this.get("x");
	}
	
	public void setY(double y) {
		this.put("y", y);
	}
	
	public double getY() {
		return this.get("y");
	}

	public void setError(double error) {
		this.put("error", error);
	}
	
	public double getError() {
		return this.get("error");
	}
	
	public void setVel(double vel) {
		this.put("vel", vel);
	}
	
	public double getVel() {
		return this.get("vel");
	}
	
	public void setPos(double pos) {
		this.put("pos", pos);
	}
	
	public double getPos(double pos) {
		return this.get("pos");
	}
	
	/*
	 * following methods primarily for drive train
	 * */
	
	public void setAngPos(double angPos) {
		this.put("angPos", angPos);
	}
	
	public double getAngPos() {
		return this.get("angPos");
	}
	
	public void setAngVel(double angVel) {
		this.put("angVel", angVel);
	}
	
	public double getAngVel() {
		return this.get("angVel");
	}
	
	public void setLeftVel(double leftVel) {
		this.put("leftVel", leftVel);
	}
	
	public double getLeftVel() {
		return this.get("leftVel");
	}
	
	public void setRightVel(double rightVel) {
		this.put("rightVel", rightVel);
	}
	
	public double getRightVel() {
		return this.get("rightVel");
	}
	
	public void leftPos(double leftPos) {
		this.put("leftPos", leftPos);
	}
	
	public double getLeftPos() {
		return this.get("leftPos");
	}
	
	public void rightPos(double rightPos) {
		this.put("rightPos", rightPos);
	}
	
	public double getRightPos() {
		return this.get("rightPos");
	}
	
}
