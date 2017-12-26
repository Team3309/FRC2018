package library.controllers.drive;

import library.controllers.Controller;
import library.controllers.pid.PIDPositionController;
import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

import org.usfirst.team3309.subsystems.Drive;

/**
 * @author Chase.Blagden Controller for following list of real world unit
 *         waypoints in curved path
 */
public class DriveCurvatureFollowerController extends Controller {

	private Waypoint[] waypoints;
	private int waypointIndex = 0;

	private PIDPositionController turningController;

	// how close robot must be before moving to next waypoint (inches)
	private double radius = 10;

	// final direction robot must face (degrees)
	private double finalHeading = 0.0;

	// inches per s
	private final double maxForwardVelocity = 100;
	private final double maxAngularVelocity = 100;

	// initialization of initial position of robot
	double curX = 0.0;
	double curY = 0.0;

	public DriveCurvatureFollowerController(Waypoint[] waypoints, double finalHeading) {
		this.waypoints = waypoints;
		this.finalHeading = finalHeading;
		turningController = new PIDPositionController(0.0, 0.0, 0.0);
		turningController.setName("turningController");
		turningController.setSubsystemID(getSubsystemID());
		turningController.setIsCompletable(true);
		turningController.setTHRESHOLD(30);
		turningController.setTIME_TO_BE_COMPLETE_S(0.25);
	}

	@Override
	public OutputSignal getOutputSignal(InputState input) {

		// calculate horizontal and vertical coordinates
		curX += input.getVel() * Math.cos(input.getAngPos());
		curY += input.getVel() * Math.sin(input.getAngPos());

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
			 */
			if (goalVelocity > maxForwardVelocity) {
				goalVelocity = maxForwardVelocity;
			}
			if (goalAngularVelocity > maxAngularVelocity) {
				goalAngularVelocity = maxAngularVelocity;
			}

			signal = new OutputSignal();

			// if on final waypoint, adjust toward final heading
			if (waypointIndex == waypoints.length - 1) {
				InputState turningInput = new InputState();
				turningInput.setError(finalHeading - input.getAngPos());
				OutputSignal turningOutput = turningController
						.getOutputSignal(turningInput);
				double turningVelocity = turningOutput.getMotor();
				signal.setLeftRightMotor(goalVelocity + turningVelocity,
						goalVelocity - turningVelocity);
				return signal;
			} else {
				signal.setLeftRightMotor(goalVelocity + goalAngularVelocity,
						goalVelocity - goalAngularVelocity);
				return signal;
			}
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
		// turning controller can only be done when on last waypoint
		return turningController.isCompleted();
	}

	/*
	 * @return distance between two points
	 */
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

	public void setFinalHeading(double finalHeading) {
		this.finalHeading = finalHeading;
	}

}
