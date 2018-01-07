package lib.controllers.drive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.controllers.Controller;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;
import org.usfirst.frc.team3309.robot.Robot;

public class PurePursuitController extends Controller {

    private Waypoint[] path;
    private int curPathIndex = 1;

    private double totalDistance = 0;
    private double curRadius = 0;

    private final double goalVelocity = 800;

    private final double WHEELBASE_INCHES;

    private boolean isFinished = false;

    public PurePursuitController(Waypoint[] path, double WHEELBASE_INCHES) {
        this.path = path;
        this.WHEELBASE_INCHES = WHEELBASE_INCHES;
        totalDistance += Math.hypot(path[1].x, path[1].y);
        curRadius = path[1].radius;
    }

    @Override
    public OutputSignal getOutputSignal(InputState inputState) {

        OutputSignal signal = new OutputSignal();

        double l = inputState.getPos();
        double dy = path[curPathIndex].y - path[curPathIndex  - 1].y;
        double dx = path[curPathIndex].x - path[curPathIndex  - 1].x;
        curRadius = path[curPathIndex].radius;

        if (l > totalDistance) {
            curPathIndex++;
            double curAngle = Math.atan2(dy, dx);
            totalDistance += curRadius * curAngle;
            if (curPathIndex >= path.length) {
                isFinished = true;
                signal.setLeftRightMotor(0, 0);
                return signal;
            }
        }

        SmartDashboard.putNumber("l", l);
        SmartDashboard.putNumber("totalDistance", totalDistance);

        System.out.println("l"+ l);
        System.out.println("totalDistance" + totalDistance);
        double leftVelocity = goalVelocity * (WHEELBASE_INCHES / curRadius);
        double rightVelocity = goalVelocity;


        signal.setLeftRightMotor(leftVelocity, rightVelocity);
        return signal;
    }

    @Override
    public boolean isCompleted() {
        return isFinished;
    }

}
