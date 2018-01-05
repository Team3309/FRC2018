package lib.controllers.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    private PIDVelocityController linearFollower;
    private PIDVelocityController angleFollower;
    private Waypoint[] path;
    private int curPathIndex = 0;
    private final double radialTolerance = 4;
    private final double maxLinearVelocity = 0.4;
    private boolean isFinished = false;

    public PurePursuitController(Waypoint[] path) {
        this.path = path;

        linearFollower = new PIDVelocityController(0.07, 0.0, 0.0, 0.017);
        linearFollower.setIsCompletable(true);
        linearFollower.setErrorThreshold(0.5);
        linearFollower.setName("Left Follower");
        linearFollower.setTimeoutS(0.2);

        angleFollower = new PIDVelocityController(0.006, 0.0, 0.0, 0.0);
        angleFollower.setIsCompletable(true);
        angleFollower.setErrorThreshold(10);
        angleFollower.setName("Right Follower");
        angleFollower.setTimeoutS(0.2);
    }

    @Override
    public OutputSignal getOutputSignal(InputState inputState) {
        OutputSignal signal = new OutputSignal();

        double x = inputState.getPos() * Math.cos(Math.toRadians(inputState.getAngPos() + 90));
        double y = inputState.getPos() * Math.sin(Math.toRadians(inputState.getAngPos() + 90));

        SmartDashboard.putNumber("X: ", x);
        SmartDashboard.putNumber("Y: ", y);

        BlackBox.writeLog(String.valueOf(x), String.valueOf(y));
        SmartDashboard.putNumber("curPathIndex: ", curPathIndex);
        SmartDashboard.putNumber("Path length: ", path.length);

        double dx = path[curPathIndex].x - x;
        double dy = path[curPathIndex].y - y;
        double l = Math.hypot(dx, dy);

        if (l < radialTolerance) {
            curPathIndex++;
            if (curPathIndex == path.length) {
                isFinished = true;
                signal.setLeftRightMotor(0, 0);
                return signal;
            }
        }

        double robotAngle = inputState.getAngPos();
        double goalAngle = path[curPathIndex].heading;
        double diffAngle = goalAngle - robotAngle;
        double yP = Math.sin(Math.toRadians(diffAngle)) / l;
        double gamma = (2 * yP) / (l * l);
        double aimTransVel = l * maxLinearVelocity;
        double aimAngVel = gamma * aimTransVel;

        if (aimTransVel > maxLinearVelocity) {
            aimTransVel = maxLinearVelocity;
        }

        SmartDashboard.putNumber("l: ", l);
        SmartDashboard.putNumber("gamma: ", gamma);
        SmartDashboard.putNumber("aimTransVel: ", aimTransVel);

        signal.setLeftRightMotor(aimTransVel + aimAngVel, aimTransVel - aimAngVel);
        return signal;
    }

    @Override
    public boolean isCompleted() {
        return isFinished;
    }


}
