package lib.controllers.drive;

import lib.communications.BlackBox;
import lib.controllers.Controller;
import lib.controllers.pid.PIDVelocityController;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;

/*
 * https://github.com/AtsushiSakai/PythonRobotics/blob/master/PathTracking/pure_pursuit/pure_pursuit.py
 * https://github.com/Team254/FRC-2017-Public/blob/master/src/com/team254/lib/util/control/AdaptivePurePursuitController.java
 * */
public class PurePursuitController extends Controller {

	private PIDVelocityController leftFollower;
	private PIDVelocityController rightFollower;
	private Waypoint[] path;
	private int curPathIndex = 0;
	private final double radialTolerance = 10;
	private final double maxLinearVelocity = 0;
	private boolean isFinished = false;

	public PurePursuitController(Waypoint[] path) {
		this.path = path;
		
		leftFollower = new PIDVelocityController(0.0, 0.0, 0.0, 0.0);
		leftFollower.setIsCompletable(true);
		leftFollower.setErrorThreshold(20);
		leftFollower.setName("Left Follower");
		leftFollower.setTimeoutS(0.2);
		
		rightFollower = new PIDVelocityController(0.0, 0.0, 0.0, 0.0);
		rightFollower.setIsCompletable(true);
		rightFollower.setErrorThreshold(20);
		rightFollower.setName("Right Follower");
		rightFollower.setTimeoutS(0.2);
	}

	@Override
	public OutputSignal getOutputSignal(InputState inputState) {
		OutputSignal signal = new OutputSignal();

		double x = inputState.getPos() * Math.cos(Math.toDegrees(inputState.getAngPos() + 90));
		double y = inputState.getPos() * Math.sin(Math.toDegrees(inputState.getAngPos() + 90));

		BlackBox.writeLog(String.valueOf(x), String.valueOf(y));

		if (curPathIndex < path.length) {
			double dx = path[curPathIndex].x - x;
			double dy = path[curPathIndex].y - y;
			double l = Math.hypot(dx, dy);
			if (l < radialTolerance) {
				curPathIndex++;
			}
			
			double robotAngle = inputState.getAngPos();
			double goalAngle = path[curPathIndex].heading;
			double diffAngle = goalAngle - robotAngle;
			double yP = Math.sin(Math.toRadians(diffAngle)) / l;
			double gamma = (2 * yP) / (l * l);
			double aimTransVel = l * maxLinearVelocity;
			if (aimTransVel > maxLinearVelocity) {
				aimTransVel = maxLinearVelocity;
			}
			signal.setLeftRightMotor(aimTransVel + gamma, aimTransVel - gamma);
			return signal;
		} else {
			isFinished = true;
			signal.setLeftRightMotor(0, 0);
			return signal;
		}
	}

	@Override
	public boolean isCompleted() {
		return isFinished;
	}
	
	

}
