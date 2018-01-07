package lib.controllers.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.controllers.Controller;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;
import org.usfirst.frc.team3309.robot.Robot;

public class PurePursuitController extends Controller {

    private Waypoint[] path;
    private int curPathIndex = 1;

    private double totalDistance = 0;

    private final double radialTolerance = 2;
    private final double goalVelocity = 400;

    private final double WHEELBASE_INCHES;
    private final double WHEEL_DIAMETER_INCHES;

    private boolean isFinished = false;

    public PurePursuitController(Waypoint[] path, double WHEELBASE_INCHES, double WHEEL_DIAMETER_INCHES) {
        this.path = path;
        this.WHEELBASE_INCHES = WHEELBASE_INCHES;
        this.WHEEL_DIAMETER_INCHES = WHEEL_DIAMETER_INCHES;
        totalDistance += Math.hypot(path[1].x, path[1].y);
    }

    @Override
    public OutputSignal getOutputSignal(InputState inputState) {

        OutputSignal signal = new OutputSignal();

        double l = inputState.getPos();

        SmartDashboard.putNumber("L: ", l);

        double dy = path[curPathIndex].y - path[curPathIndex  - 1].y;
        double dx = path[curPathIndex].x - path[curPathIndex  - 1].x;

        if (l > totalDistance) {
            curPathIndex++;
            totalDistance += Math.hypot(dx, dy);
            if (curPathIndex >= path.length) {
                isFinished = true;
                signal.setLeftRightMotor(0, 0);
                return signal;
            }
        }

        SmartDashboard.putNumber("Total distance: ", totalDistance);
        SmartDashboard.putNumber("Goal x", path[curPathIndex].x);
        SmartDashboard.putNumber("Goal y", path[curPathIndex].y);
        SmartDashboard.putNumber("curPathIndex", curPathIndex);

        double goalHeading = Math.atan2(dy, dx);
        double errorHeading = goalHeading - inputState.getAngPos();

        double curvature = (WHEEL_DIAMETER_INCHES * errorHeading) / WHEELBASE_INCHES;
        double nativeCountsCurvature = -Robot.drive.inchesToEncoderCounts(curvature);

        SmartDashboard.putNumber("Curvature:", curvature);

        signal.setLeftRightMotor(goalVelocity - nativeCountsCurvature, goalVelocity + nativeCountsCurvature);
        return signal;
    }

    @Override
    public boolean isCompleted() {
        return isFinished;
    }


}
