package library.controllers.drive;

import library.controllers.Controller;
import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

import org.usfirst.frc.team3309.robot.Sensors;
import org.usfirst.team3309.subsystems.Drive;


/**
 * @author Chase.Blagden
 *	Controller for following list of real world unit waypoints in curved path
 *	TODO: add controller for final heading
 */
public class DriveCurvatureFollower extends Controller {

	private Waypoint[] waypoints;
	private int waypointIndex = 0;

	// how close robot must be before moving to next waypoint (inches)
	private double radius = 10;

	// inches per s
	private final double maxForwardVelocity = 100;
	private final double maxAngularVelocity = 100;

	// initialization of initial position of robot
	double curX = 0.0;
	double curY = 0.0;

	public DriveCurvatureFollower(Waypoint[] waypoints) {
		this.waypoints = waypoints;
	}

	@Override
	public OutputSignal getOutputSignal(InputState input) {

		// calculate horizontal and vertical coordinates
		curX += Sensors.getVel() * Math.cos(Sensors.getAngle());
		curY += Sensors.getVel() * Math.sin(Sensors.getAngle());
		
		Waypoint curWaypoint = new Waypoint(curX, curY);
		curWaypoint.convertToEncoderCounts();

		OutputSignal signal;
		
		if (waypoints != null) {

			Waypoint goalWaypoint = waypoints[waypointIndex];
			goalWaypoint.convertToEncoderCounts();

			// when within certain range from a waypoint, change waypoint
			if (getDistance(goalWaypoint, curWaypoint) < Drive.getInstance()
					.getEncoderCounts(radius)) {
				waypointIndex++;

				// when on last waypoint, stop moving
				if (waypointIndex == waypoints.length) {
					waypointIndex--;
					signal = new OutputSignal();
					signal.setLeftRightMotor(0.0, 0.0);
					return signal;
				} else {
					goalWaypoint = waypoints[waypointIndex];
				}
				
			}

			// find distance between points
			curWaypoint = goalWaypoint.subtract(curWaypoint);

			// set velocities proportional to distance from target
			double goalVelocity = curWaypoint.y
					* Drive.getInstance().getEncoderCountsPerMs(
							maxForwardVelocity);
			double goalAngularVelocity = curWaypoint.x
					* Drive.getInstance().getEncoderCountsPerMs(
							maxAngularVelocity);

			/*
			 * Truncate velocities if too high
			 * */
			if (goalVelocity > maxForwardVelocity) {
				goalVelocity = maxForwardVelocity;
			}
			if (goalAngularVelocity > maxAngularVelocity) {
				goalAngularVelocity = maxAngularVelocity;
			}

			signal = new OutputSignal();
			signal.setLeftRightMotor(goalVelocity + goalAngularVelocity,
					goalVelocity - goalAngularVelocity);
			return signal;
		} else {
			signal = new OutputSignal();
			signal.setLeftRightMotor(0.0, 0.0);
			return signal;
		}
	}

	@Override
	public void reset() {

	}

	@Override
	public boolean isCompleted() {
		return waypoints.length - 1 == waypointIndex;
	}

	/*
	 * @return 
	 * 	distance between two points
	 * */
	public double getDistance(Waypoint a, Waypoint b) {
		return Math.hypot(a.x - b.x, a.y - b.y);
	}

	/*
	 * Initial position of robot for auto
	 * 
	 * @params double x - inches going up from bottom left of field relative to
	 * robot double y - inches going up from bottom left of field relative to
	 * robot
	 */
	public void setCurPos(double x, double y) {
		this.curX = Drive.getInstance().getEncoderCounts(x);
		this.curY = Drive.getInstance().getEncoderCounts(y);
	}

}
