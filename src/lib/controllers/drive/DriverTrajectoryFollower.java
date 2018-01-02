package lib.controllers.drive;

import lib.controllers.Controller;
import lib.controllers.pid.PIDVelocityController;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;

public class DriverTrajectoryFollower extends Controller {

	private PIDVelocityController followerLeft;
	private PIDVelocityController followerRight;
	private Pose[] path;
	private int curPathIndex = 0;
	private final double direction = 1.0;
	private final double kTurn = 0;
	private final double radius = 5;

	public DriverTrajectoryFollower(Pose[] poses) {
		this.path = poses;
		followerLeft = new PIDVelocityController(0.0, 0, 0.0, 0.0);
		followerRight = new PIDVelocityController(0.0, 0.0, 0.0, 0.0);
	}

	@Override
	public OutputSignal getOutputSignal(InputState inputState) {
		double distanceL = direction * inputState.getLeftPos();
		double distanceR= direction * inputState.getRightPos();
		
		double leftError = path[curPathIndex].leftDistance - distanceL;
		double rightError = path[curPathIndex].rightDistance - distanceR;
		
		if (Math.abs(leftError) < radius && Math.abs(rightError) < radius) {
			curPathIndex++;
		}
		
		InputState inputLeft = new InputState();
		inputLeft.setError(leftError);
		
		InputState inputRight = new InputState();
		inputRight.setPos(rightError);
		
		double speedLeft = direction * followerLeft.getOutputSignal(inputLeft).getMotor();
		double speedRight = direction * followerRight.getOutputSignal(inputRight).getMotor();

		double goalHeading = path[curPathIndex].heading;
		double observedHeading = -inputState.getAngPos();
		
		double angleDiff = Math.toDegrees(getDifferenceInAngleDegrees(observedHeading, goalHeading));
		double turn = kTurn * angleDiff;
		
		
		OutputSignal signal = new OutputSignal();
		signal.setLeftRightMotor(speedLeft + turn, speedRight - turn);
		return signal;
	}

	@Override
	public boolean isCompleted() {
		return false;
	}

	public static double getDifferenceInAngleDegrees(double from, double to) {
		return boundAngleNeg180to180Degrees(to - from);
	}

	public static double boundAngleNeg180to180Degrees(double angle) {
		// Naive algorithm
		while (angle >= 180.0) {
			angle -= 360.0;
		}
		while (angle < -180.0) {
			angle += 360.0;
		}
		return angle;
	}

}
