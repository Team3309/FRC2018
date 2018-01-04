package lib.controllers.drive;

/*
 * Represents series of points during auto for setting drivetrain
 * @author Chase Blagden
 * */
public class VelocityChangePoint {

	private double leftVel;
	private double rightVel;
	private double enc;
	private Double goalAngle = null;

	public VelocityChangePoint(double enc, double vel) {
		this.leftVel = vel;
		this.rightVel = vel;
		this.enc = enc;
	}

	public VelocityChangePoint(double enc, double leftVel, double rightVel) {
		this.leftVel = leftVel;
		this.rightVel = rightVel;
		this.enc = enc;
	}

	public VelocityChangePoint(double enc, double leftVel, double rightVel,
			double goalAngle) {
		this.leftVel = leftVel;
		this.rightVel = rightVel;
		this.enc = enc;
		this.goalAngle = goalAngle;
	}

	public double getNewLeftVel() {
		return leftVel;
	}

	public double getNewRightVel() {
		return rightVel;
	}

	public double getEncValueToChangeAt() {
		return enc;
	}

	public void setGoalAngle(double goalAngle) {
		this.goalAngle = goalAngle;
	}

	public Double getGoalAngle() {
		return goalAngle;
	}

}
