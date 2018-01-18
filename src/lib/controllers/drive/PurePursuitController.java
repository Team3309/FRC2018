package lib.controllers.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.controllers.Finishable;
import org.usfirst.frc.team3309.robot.Robot;

import java.util.ArrayList;

public class PurePursuitController extends DriveController implements Finishable {

    private final ArrayList<Waypoint> path;

    private double goalDistance;
    private double curRadius;
    private double goalAngle;

    private int curPathIndex = 0;

    private double curPos;
    private double curAngle;

    private final double goalVelocity = 800;
    private final double WHEELBASE_INCHES;

    public PurePursuitController(ArrayList<Waypoint> path, double WHEELBASE_INCHES) {
        this.path = path;
        this.WHEELBASE_INCHES = WHEELBASE_INCHES;
        curRadius = this.path.get(0).radius;
        goalAngle = this.path.get(0).angle;
        goalDistance += Math.abs(goalAngle) * curRadius;
    }

    @Override
    public void update() {
        double leftVelocity;
        double rightVelocity;

        if (curPos > goalDistance) {
            curPathIndex++;
            System.out.println("curPathIndex" + curPathIndex);
            System.out.println("goalDistance" + goalDistance);
            System.out.println("pos" + curPos);
            System.out.println("angPos" + curAngle);
            System.out.println("goalAngle" + goalAngle);
            if (isFinished()) {
                driveSignal = new DriveSignal(0, 0);
            } else {
                goalAngle = path.get(curPathIndex).angle;
                curRadius = path.get(curPathIndex).radius;
                goalDistance += Math.abs(goalAngle) * curRadius;
            }
        }

        if (goalAngle > 0) {
            leftVelocity = getTurningValue();
            rightVelocity = goalVelocity;
        } else if (goalAngle < 0) {
            rightVelocity = getTurningValue();
            leftVelocity = goalVelocity;
        } else {
            leftVelocity = goalVelocity;
            rightVelocity = goalVelocity;
        }

        SmartDashboard.putNumber("desLeftVel", leftVelocity);
        SmartDashboard.putNumber("deRightVel", rightVelocity);
        SmartDashboard.putNumber("path length", path.size());
        SmartDashboard.putNumber("curDistance", curPos);
        SmartDashboard.putNumber("goalDistance", goalDistance);
        SmartDashboard.putNumber("curPathIndex", curPathIndex);
        SmartDashboard.putNumber("curRadius", curRadius);
    }

    public DriveSignal update(double curPos, double curAngle) {
        this.curPos = curPos;
        this.curAngle = curAngle;
        update();
        return driveSignal;
    }

    private double getTurningValue() {
        return goalVelocity * Robot.drive.inchesToEncoderCounts(((curRadius - (WHEELBASE_INCHES / 2)) / (curRadius + (WHEELBASE_INCHES / 2))));
    }

    @Override
    public boolean isFinished() {
        return curPathIndex >= path.size();
    }

}
