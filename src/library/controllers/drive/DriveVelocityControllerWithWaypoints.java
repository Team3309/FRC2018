package library.controllers.drive;

import java.util.LinkedList;
import java.util.List;

import library.ChaseTimer;
import library.controllers.Controller;
import library.controllers.pid.PIDPositionController;
import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

import org.usfirst.frc.team3309.robot.Sensors;
import org.usfirst.team3309.subsystems.Drive;

/**
 * @author Chase.Blagden
 * 
 */
public class DriveVelocityControllerWithWaypoints extends Controller {

	private double goal;
	private double angle;
	private ChaseTimer doneTimer;
	private final double DEFAULT_VELOCITY = 1000;
	private List<Waypoint> waypoints = new LinkedList<Waypoint>();
	private PIDPositionController turningController;

	public DriveVelocityControllerWithWaypoints(double goal) {
		Drive.getInstance().changeToVelocityMode();
		turningController = new PIDPositionController(0.0, 0.0, 0.0);
		turningController.setName("angle-controller");
		turningController.setIsCompletable(true);
		turningController.setSubsystemID(this.getSubsystemID());
		turningController.setTHRESHOLD(100);
		turningController.setTIME_TO_BE_COMPLETE_S(0.25);
		this.goal = goal;
	}
	

	@Override
	public OutputSignal getOutputSignal(InputState inputState) {
		
		Waypoint curWaypoint = new Waypoint(DEFAULT_VELOCITY, 0);
		double closestPoint = Double.MAX_VALUE;
		
		// checks for closest waypoint
		for (Waypoint waypoint : waypoints) {
			if (Math.abs(Sensors.getPos()) > Math.abs(waypoint.getEncValueToChangeAt())) {
				if (Math.abs(Math.abs(Sensors.getPos()) - Math.abs(waypoint.getEncValueToChangeAt())) < closestPoint) {
					curWaypoint = waypoint;
					closestPoint = Math.abs(Math.abs(Sensors.getPos()) - Math.abs(waypoint.getEncValueToChangeAt()));
				}
			}
		}
		
		double rightAimVel = curWaypoint.getNewRightVel();
		double leftAimVel = curWaypoint.getNewLeftVel();
		
		if (curWaypoint.getGoalAngle() == null) {
			curWaypoint.setGoalAngle(Sensors.getAngle());
		}
		angle = curWaypoint.getGoalAngle();
		
		OutputSignal signal = new OutputSignal();
		if (rightAimVel == leftAimVel) {
			InputState turningInput = new InputState();
			turningInput.setError(angle - inputState.getAngPos());
			double turnPower = turningController.getOutputSignal(turningInput).getMotor();
			signal.setLeftRightMotor(leftAimVel + turnPower, rightAimVel - turnPower);
		} else {
			signal.setLeftRightMotor(leftAimVel, rightAimVel);
		}
		
		return signal;
	}
	
	@Override
	public boolean isCompleted() {
		return this.doneTimer.isConditionMaintained(Math.abs(Drive.getInstance().getDistanceTraveled()) > Math.abs(goal));
	}

	@Override
	public void reset() {
		doneTimer.reset();
	}
	
	public void setWaypoints(List<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}

}
