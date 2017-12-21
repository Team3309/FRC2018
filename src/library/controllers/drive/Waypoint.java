package library.controllers.drive;

/*
 * Represents series of points during auto for setting drivetrain
 * @author Chase Blagden
 * */
public class Waypoint {

	private double leftVel;
	private double rightVel;
	private double enc;
	private Double goalAngle = null;
	
	public Waypoint(double vel, double enc) {
		this.leftVel = vel;
		this.rightVel = vel;
		this.enc = enc;
	}
	
	public Waypoint(double leftVel, double rightVel, double enc) {
		this.leftVel = leftVel;
		this.rightVel = rightVel;
		this.enc = enc;
	}
	
	public Waypoint(double leftVel, double rightVel, double enc, double goalAngle) {
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
	
	public double getGoalAngle() {
		return goalAngle;
	}
	
	
}
