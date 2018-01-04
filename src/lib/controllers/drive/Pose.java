package lib.controllers.drive;

public class Pose {
	
	protected double leftDistance;
	protected double rightDistance;
	protected double leftVelocity;
	protected double rightVelocity;
	protected double heading;
	protected double headingVelocity;
	
	public Pose(double leftDistance, double rightDistance, double leftVelocity, double rightVelocity, double heading,
			double headingVelocity) {
		this.leftDistance = leftDistance;
		this.rightDistance = rightDistance;
		this.leftVelocity = leftVelocity;
		this.rightVelocity = rightVelocity;
		this.heading = heading;
		this.headingVelocity = headingVelocity;
	}

}
