package lib.controllers.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.controllers.Controller;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;

public class PurePursuitController extends Controller {

    private Waypoint[] path;
    private int curPathIndex = 0;

    private final double radialTolerance = 2;
    private final double goalVelocity = 15;

    private final double WHEELBASE_INCHES;
    private final double WHEEL_DIAMETER_INCHES;

    private boolean isFinished = false;

    private double x = 0;
    private double y = 0;

    public PurePursuitController(Waypoint[] path, double WHEELBASE_INCHES, double WHEEL_DIAMETER_INCHES) {
        this.path = path;
        this.WHEELBASE_INCHES = WHEELBASE_INCHES;
        this.WHEEL_DIAMETER_INCHES = WHEEL_DIAMETER_INCHES;
    }

    @Override
    public OutputSignal getOutputSignal(InputState inputState) {
        OutputSignal signal = new OutputSignal();

        x += inputState.getVel() * Math.cos(Math.toRadians(inputState.getAngPos() + 90));
        y += inputState.getVel() * Math.sin(Math.toRadians(inputState.getAngPos() + 90));

        SmartDashboard.putNumber("X: ", x);
        SmartDashboard.putNumber("Y: ", y);


        double dx = path[curPathIndex].x - x;
        double dy = path[curPathIndex].y - y;
        double l = Math.hypot(dx, dy);

/*        if (l < radialTolerance) {
            curPathIndex++;
            if (curPathIndex == path.length) {
                isFinished = true;
                signal.setLeftRightMotor(0, 0);
                return signal;
            }
        }*/

        double goalX = path[curPathIndex].x;
        double goalY = path[curPathIndex].y;

        double curHeading = Math.toRadians(inputState.getAngPos());
        double curRadius = path[curPathIndex].radius;

        double xError = goalX - x * curRadius;
        double yError = goalY - y * curRadius;

        double xGoalVelocity = xError * Math.cos(curHeading) + yError * Math.sin(curHeading);
        double yGoalVelocity = -xError * Math.sin(curHeading) + yError * Math.cos(curHeading);

        double goalHeading = Math.atan2(yGoalVelocity, xGoalVelocity);
        double errorHeading = goalHeading - inputState.getAngPos();

        double curvature = WHEEL_DIAMETER_INCHES * errorHeading / WHEELBASE_INCHES;

        signal.setLeftRightMotor(goalVelocity - curvature, goalVelocity + curvature);
        return signal;
    }

    @Override
    public boolean isCompleted() {
        return isFinished;
    }


}
