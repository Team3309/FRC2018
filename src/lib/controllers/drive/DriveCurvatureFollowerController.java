package lib.controllers.drive;

import lib.controllers.Controller;
import lib.controllers.pid.PIDPositionController;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;

/**
 * @author Chase.Blagden Controller for following list of real world unit
 *         waypoints in curved path
 */
public class DriveCurvatureFollowerController extends Controller {

	private Waypoint[] waypoints;
	private int waypointIndex = 0;

	private Waypoint curWaypoint;

	private PIDPositionController turningController;

	// how close robot must be before moving to next waypoint (inches)
	private double radius = 10;

	// final direction robot must face (degrees)
	private double finalHeading = 0.0;

	// inches per s
	private final double maxForwardVelocity = 100;
	private final double maxAngularVelocity = 100;

	public DriveCurvatureFollowerController(Waypoint[] waypoints) {
		this.waypoints = waypoints;
		turningController = new PIDPositionController(0.0, 0.0, 0.0);
		turningController.setName("turningController");
		turningController.setIsCompletable(true);
		turningController.setErrorThreshold(30);
		turningController.setTimeoutS(0.25);
	}

	public DriveCurvatureFollowerController(Waypoint[] waypoints,
			double finalHeading) {
		this(waypoints);
		this.finalHeading = finalHeading;
	}

	@Override
	public OutputSignal getOutputSignal(InputState inputState) {

		curWaypoint = new Waypoint(inputState.getX(), inputState.getY(), inputState.getAngPos());

		OutputSignal signal = new OutputSignal();

		if (waypoints != null) {

			Waypoint goalWaypoint = waypoints[waypointIndex];

			// when within certain range from a waypoint, change waypoint
			if (Waypoint.getDistance(goalWaypoint, curWaypoint) < radius) {
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
			curWaypoint = new Waypoint(goalWaypoint.y - curWaypoint.y, goalWaypoint.x - curWaypoint.x, goalWaypoint.heading - curWaypoint.heading);

			// set velocities proportional to distance from target
			double goalVelocity = curWaypoint.y * maxForwardVelocity;
			double goalAngularVelocity = curWaypoint.x * maxAngularVelocity;
			
			/*
			 * Truncate velocities if too high
			 */
			if (goalVelocity > maxForwardVelocity) {
				goalVelocity = maxForwardVelocity;
			}
			if (goalAngularVelocity > maxAngularVelocity) {
				goalAngularVelocity = maxAngularVelocity;
			}


			// if on final waypoint, adjust toward final heading
			if (waypointIndex == waypoints.length - 1) {
				InputState turningInput = new InputState();
				turningInput.setError(finalHeading - inputState.getAngPos());
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
			signal.setLeftRightMotor(0.0, 0.0);
			return signal;
		}
	}

	@Override
	public void reset() {
		waypoints = null;
	}

	@Override
	public boolean isCompleted() {
		// turning controller can only be done when on last waypoint
		return turningController.isCompleted();
	}

}
