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

	/*
	 * @return
	 * 
	 * @param error
	 */
	public void setError(double error) {
		this.put("error", error);
	}

	/*
	 * @return error
	 * 
	 * @param
	 */
	public double getError() {
		return this.get("error");
	}

	/*
	 * @return
	 * 
	 * @param velocity
	 */
	public void setVel(double vel) {
		this.put("vel", vel);
	}

	/*
	 * @return velocity
	 * 
	 * @param
	 */
	public double getVel() {
		return this.get("vel");
	}

	/*
	 * @return
	 * 
	 * @param position
	 */
	public void setPos(double pos) {
		this.put("pos", pos);
	}

	/*
	 * @return position
	 * 
	 * @param
	 */
	public double getPos() {
		return this.get("pos");
	}

	/*
	 * ------------------------------------------------- -----Methods primarily
	 * used for drive train-----
	 * -------------------------------------------------
	 */

	/*
	 * @return
	 * 
	 * @param x - gyro
	 */
	public void setX(double x) {
		this.put("x", x);
	}

	/*
	 * @return x - gyro
	 * 
	 * @param
	 */
	public double getX() {
		return this.get("x");
	}

	/*
	 * @return
	 * 
	 * @param y - gyro
	 */
	public void setY(double y) {
		this.put("y", y);
	}

	/*
	 * @return y - gyro
	 * 
	 * @param
	 */
	public double getY() {
		return this.get("y");
	}

	/*
	 * @return
	 * 
	 * @param z - gyro
	 */
	public void setZ(double z) {
		this.put("z", z);
	}

	/*
	 * @return z - gyro
	 * 
	 * @param
	 */
	public double getZ() {
		return this.get("z");
	}

	/*
	 * @return
	 * 
	 * @param drive angular position
	 */
	public void setAngPos(double angPos) {
		this.put("angPos", angPos);
	}

	/*
	 * @return drive angular position
	 * 
	 * @param
	 */
	public double getAngPos() {
		return this.get("angPos");
	}

	/*
	 * @return
	 * 
	 * @param drive angular velocity
	 */
	public void setAngVel(double angVel) {
		this.put("angVel", angVel);
	}

	/*
	 * @return drive angular velocity
	 * 
	 * @param
	 */
	public double getAngVel() {
		return this.get("angVel");
	}

	/*
	 * @return
	 * 
	 * @param left drive velocity
	 */
	public void setLeftVel(double leftVel) {
		this.put("leftVel", leftVel);
	}

	/*
	 * @return left drive velocity
	 * 
	 * @param
	 */
	public double getLeftVel() {
		return this.get("leftVel");
	}

	/*
	 * @return
	 * 
	 * @param right drive velocity
	 */
	public void setRightVel(double rightVel) {
		this.put("rightVel", rightVel);
	}

	/*
	 * @return right drive velocity
	 * 
	 * @param
	 */
	public double getRightVel() {
		return this.get("rightVel");
	}

	/*
	 * @return
	 * 
	 * @param left drive position
	 */
	public void leftPos(double leftPos) {
		this.put("leftPos", leftPos);
	}

	/*
	 * @return left drive position
	 * 
	 * @param
	 */
	public double getLeftPos() {
		return this.get("leftPos");
	}

	/*
	 * @return
	 * 
	 * @param right drive position
	 */
	public void setRightPos(double rightPos) {
		this.put("rightPos", rightPos);
	}

	/*
	 * @return right drive position
	 * 
	 * @param
	 */
	public double getRightPos() {
		return this.get("rightPos");
	}

}
