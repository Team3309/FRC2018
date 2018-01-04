package lib.controllers.drive;

import java.util.LinkedList;
import java.util.List;

import lib.ChaseTimer;
import lib.controllers.Controller;
import lib.controllers.pid.PIDPositionController;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;

import org.usfirst.frc.team3309.robot.Robot;

/**
 * @author Chase.Blagden
 * 
 */
public class DriveVelocityControllerWithSetpoints extends Controller {

	private final double goal;
	private final double timeoutS = 0.25;
	private ChaseTimer doneTimer = new ChaseTimer(timeoutS);
	private final double defaultVelocity = 1000;
	private List<VelocityChangePoint> setpoints = new LinkedList<VelocityChangePoint>();
	private PIDPositionController turningController;

	public DriveVelocityControllerWithSetpoints(double goal) {
		turningController = new PIDPositionController(0.0, 0.0, 0.0);
		turningController.setName("angle-controller");
		turningController.setIsCompletable(true);
		turningController.setErrorThreshold(100);
		turningController.setTimeoutS(0.25);
		this.goal = goal;
	}

	@Override
	public OutputSignal getOutputSignal(InputState inputState) {
		
		VelocityChangePoint curSetpoint = new VelocityChangePoint(defaultVelocity, 0);
		
		double closestPoint = Double.MAX_VALUE;

		// checks for closest waypoint
		for (VelocityChangePoint setpoint : setpoints) {
			if (Math.abs(inputState.getPos()) > Math.abs(setpoint.getEncValueToChangeAt())) {
				if (Math.abs(Math.abs(inputState.getPos()) - Math.abs(setpoint.getEncValueToChangeAt())) < closestPoint) {
					curSetpoint = setpoint;
					closestPoint = Math.abs(Math.abs(inputState.getPos()) - Math.abs(setpoint.getEncValueToChangeAt()));
				}
			}
		}

		double rightAimVel = curSetpoint.getNewRightVel();
		double leftAimVel = curSetpoint.getNewLeftVel();

		if (curSetpoint.getGoalAngle() == null) {
			curSetpoint.setGoalAngle(inputState.getAngPos());
		}

		double angle = curSetpoint.getGoalAngle();

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
		return this.doneTimer.isConditionMaintained(Math.abs(Robot.drive.getEncoderPos()) > Math.abs(goal))
				&& this.turningController.isCompleted();
	}

	@Override
	public void reset() {
		doneTimer.reset();
		turningController.reset();
	}

	public void setSetpoints(List<VelocityChangePoint> setpoints) {
		this.setpoints = setpoints;
	}

}
