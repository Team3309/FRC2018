package lib.controllers.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.controllers.Controller;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;
import org.usfirst.frc.team3309.robot.Robot;

import java.util.ArrayList;

public class PurePursuitController extends Controller {

    private final ArrayList<Waypoint> path;

    private double goalDistance;
    private double curRadius;
    private double goalAngle;

    private int curPathIndex = 0;
    private boolean isFinished = false;

    private final double goalVelocity = 800;
    private final double ANGLE_ERROR_THRESHOLD = Math.toRadians(5);
    private final double WHEELBASE_INCHES;
    private OutputSignal signal = new OutputSignal();

    public PurePursuitController(ArrayList<Waypoint> path, double WHEELBASE_INCHES) {
        this.path = path;
        this.WHEELBASE_INCHES = WHEELBASE_INCHES;
        curRadius = this.path.get(0).radius;
        goalAngle = this.path.get(0).angle;
        goalDistance += Math.abs(goalAngle) * curRadius;
    }

    @Override
    public OutputSignal getOutputSignal(InputState inputState) {

        double leftVelocity = 0;
        double rightVelocity = 0;

        if (inputState.getPos() > goalDistance ) {
            curPathIndex++;
            System.out.println("curPathIndex" + curPathIndex);
            System.out.println("goalDistance" + goalDistance);
            System.out.println("pos" + inputState.getPos());
            System.out.println("angPos" + inputState.getAngPos());
            System.out.println("goalAngle" + goalAngle);
            if (curPathIndex >= path.size()) {
                isFinished = true;
                signal.setLeftRightMotor(0, 0);
                return signal;
            } else {
                goalAngle = path.get(curPathIndex).angle;
                curRadius = path.get(curPathIndex).radius;
                goalDistance += Math.abs(goalAngle) * curRadius;
            }
        }

        SmartDashboard.putNumber("path length", path.size());
        SmartDashboard.putNumber("curDistance", inputState.getPos());
        SmartDashboard.putNumber("goalDistance", goalDistance);
        SmartDashboard.putNumber("curPathIndex", curPathIndex);
        SmartDashboard.putNumber("curRadius", curRadius);


        if (goalAngle > 0) {
            leftVelocity = goalVelocity * ((curRadius - WHEELBASE_INCHES / 2) / (curRadius + WHEELBASE_INCHES / 2));
            rightVelocity = goalVelocity;
        } else if (goalAngle < 0) {
            leftVelocity = goalVelocity * ((curRadius - WHEELBASE_INCHES / 2) / (curRadius + WHEELBASE_INCHES / 2));
            rightVelocity = goalVelocity;
        } else {
            leftVelocity = goalVelocity;
            rightVelocity = goalVelocity;
        }

        SmartDashboard.putNumber("desLeftVel", leftVelocity);
        SmartDashboard.putNumber("deRightVel", rightVelocity);

        signal.setLeftRightMotor(Robot.drive.inchesToEncoderCounts(leftVelocity), Robot.drive.inchesToEncoderCounts(rightVelocity));
        return signal;
    }

    @Override
    public boolean isCompleted() {
        return isFinished;
    }

    private boolean isAngleComplete(double curAngle) {
        return Math.abs(goalAngle - curAngle) < ANGLE_ERROR_THRESHOLD;
    }

}
