package lib.controllers.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.controllers.Controller;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;
import org.usfirst.frc.team3309.robot.Robot;

public class PurePursuitController extends Controller {

    private Waypoint[] path;
    private int curPathIndex = 1;

    private final double radialTolerance = 2;
    private final double goalVelocity = 200;

    private final double WHEELBASE_INCHES;
    private final double WHEEL_DIAMETER_INCHES;

    private boolean isFinished = false;

    private double y = 0;
    private double x = 0;

    public PurePursuitController(Waypoint[] path, double WHEELBASE_INCHES, double WHEEL_DIAMETER_INCHES) {
        this.path = path;
        this.WHEELBASE_INCHES = WHEELBASE_INCHES;
        this.WHEEL_DIAMETER_INCHES = WHEEL_DIAMETER_INCHES;
    }

    @Override
    public OutputSignal getOutputSignal(InputState inputState) {
        OutputSignal signal = new OutputSignal();

        SmartDashboard.putNumber("y: ", y);
        SmartDashboard.putNumber("x: ", x);

        double l = inputState.getPos();



        if (l < radialTolerance) {
            curPathIndex++;
            if (curPathIndex == path.length) {
                isFinished = true;
                signal.setLeftRightMotor(0, 0);
                return signal;
            }
        }

        SmartDashboard.putNumber("L: ", l);
        double goalX = path[curPathIndex].x;
        double goalY = path[curPathIndex].y;

        double curHeading = Math.toRadians(inputState.getAngPos());
        double curRadius = path[curPathIndex].radius;

        double yError = goalX - y * curRadius;
        double xError = goalY - x * curRadius;

        double xGoalVelocity = xError * Math.cos(curHeading) + yError * Math.sin(curHeading);
        double yGoalVelocity = -xError * Math.sin(curHeading) + yError * Math.cos(curHeading);

        double goalHeading = Math.atan2(yGoalVelocity, xGoalVelocity);
        double errorHeading = goalHeading - inputState.getAngPos();

        double curvature = (WHEEL_DIAMETER_INCHES * errorHeading) / WHEELBASE_INCHES;
        double nativeCountsCurvature = -Robot.drive.inchesToEncoderCounts(curvature);

        SmartDashboard.putNumber("Curvature:", curvature);
        SmartDashboard.putNumber("xGoalVelocity", xGoalVelocity);
        SmartDashboard.putNumber("yGoalVelocity", yGoalVelocity);

        signal.setLeftRightMotor(goalVelocity - nativeCountsCurvature, goalVelocity + nativeCountsCurvature);
        return signal;
    }

    @Override
    public boolean isCompleted() {
        return isFinished;
    }


}
