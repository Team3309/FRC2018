package org.usfirst.frc.team3309.lib.controllers.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.lib.controllers.Controller1;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveSignal;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveState;
import org.usfirst.frc.team3309.lib.controllers.helpers.Waypoint;
import org.usfirst.frc.team3309.lib.controllers.interfaces.Finishable;
import org.usfirst.frc.team3309.lib.math.LibMath;
import org.usfirst.frc.team3309.robot.Robot;

import java.util.ArrayList;

@Deprecated
public class BiArcController extends Controller1<DriveSignal, DriveState> implements Finishable {

    private final ArrayList<Waypoint> path;

    private double goalDistance;
    private double curRadius;
    private double goalAngle;

    private double angleErrorThreshold = Math.toRadians(5);
    private double distanceErrorThreshold = Robot.drive.inchesToEncoderCounts(1.5);

    private double prevAngle;
    private int curPathIndex = 0;

    private final double goalVelocity;
    private final double WHEELBASE;

    public BiArcController(ArrayList<Waypoint> path, double WHEELBASE, double goalVelocity) {
        this.path = path;
        this.WHEELBASE = WHEELBASE;
        curRadius = this.path.get(0).getRadius();
        goalAngle = this.path.get(0).getAngle();
        goalDistance += Math.abs(goalAngle) * Math.abs(curRadius);
        this.goalVelocity = goalVelocity;
    }

    @Override
    public DriveSignal update(DriveState driveState) {
        DriveSignal driveSignal;

        double leftVelocity;
        double rightVelocity;

        double curPos = driveState.getAveragePos();
        double curAngle = driveState.getAngPos();

        double curAngleDiff = goalAngle - curAngle;
        double curPosDiff = goalDistance - curPos;

        boolean isDistInThreshold = LibMath.isInThreshold(curPosDiff, distanceErrorThreshold);
        boolean isAngleInThreshold = LibMath.isInThreshold(curAngleDiff, angleErrorThreshold);

        SmartDashboard.putBoolean("isInAngleThresh", isAngleInThreshold);
        SmartDashboard.putBoolean("isInDistThresh", isDistInThreshold);
        SmartDashboard.putNumber("curPosDiff ", curPosDiff);
        SmartDashboard.putNumber("curAngleDiff ", curAngleDiff);

        if (isDistInThreshold) {
            curPathIndex++;
            prevAngle = curAngle;
            System.out.println("curPathIndex" + curPathIndex);
            System.out.println("goalDistance" + goalDistance);
            System.out.println("pos" + curPos);
            System.out.println("angPos" + curAngle);
            System.out.println("goalAngle" + goalAngle);
            if (isFinished()) {
                driveSignal = new DriveSignal(0, 0);
                return driveSignal;
            } else {
                goalAngle = path.get(curPathIndex).getAngle();
                curRadius = path.get(curPathIndex).getRadius();
                goalDistance += Math.abs(goalAngle) * Math.abs(curRadius);
            }
        }

        if (curRadius < 0) {
            leftVelocity = getTurningValue();
            rightVelocity = goalVelocity;
        } else if (curRadius > 0) {
            rightVelocity = getTurningValue();
            leftVelocity = goalVelocity;
        } else {
            leftVelocity = goalVelocity;
            rightVelocity = goalVelocity;
        }

        SmartDashboard.putNumber("turing value", getTurningValue());
        SmartDashboard.putNumber("desLeftVel", leftVelocity);
        SmartDashboard.putNumber("desRightVel", rightVelocity);
        SmartDashboard.putNumber("path length", path.size());
        SmartDashboard.putNumber("curDistance", curPos);
        SmartDashboard.putNumber("goalDistance", goalDistance);
        SmartDashboard.putNumber("curPathIndex", curPathIndex);
        SmartDashboard.putNumber("curRadius", curRadius);

        driveSignal = new DriveSignal(leftVelocity, rightVelocity);
        return driveSignal;
    }

    private double getTurningValue() {
        return goalVelocity * ((Math.abs(curRadius) - (WHEELBASE / 2.0)) / (Math.abs(curRadius) + (WHEELBASE / 2.0)));
    }

    @Override
    public boolean isFinished() {
        return curPathIndex >= path.size();
    }

    public void setDistanceErrorThreshold(double distanceErrorThreshold) {
        this.distanceErrorThreshold = distanceErrorThreshold;
    }

    public void setAngleErrorThreshold(double angleErrorThreshold) {
        this.angleErrorThreshold = angleErrorThreshold;
    }

}



