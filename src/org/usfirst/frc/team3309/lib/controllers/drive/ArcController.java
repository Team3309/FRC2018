package org.usfirst.frc.team3309.lib.controllers.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.lib.controllers.Controller1;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveSignal;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveState;
import org.usfirst.frc.team3309.lib.controllers.interfaces.Finishable;
import org.usfirst.frc.team3309.lib.math.LibMath;
import org.usfirst.frc.team3309.robot.Robot;

public class ArcController extends Controller1<DriveSignal, DriveState> implements Finishable {


    private double goalDistance;
    private double curRadius;

    public double getGoalAngle() {
        return goalAngle;
    }

    private double goalAngle;

    private double angleErrorThreshold = Math.toRadians(4);
    private double distanceErrorThreshold = Robot.drive.inchesToEncoderCounts(1.5);

    private double prevAngle;
    private boolean isFinished = false;

    private final double goalVelocity;
    private final double WHEELBASE;

    private double leftErr;
    private double rightErr;

    public ArcController(double radius, double ang, double WHEELBASE, double goalVelocity) {
        this.WHEELBASE = WHEELBASE;
        curRadius = radius;
//        SmartDashboard.putNumber("curRadius---------", curRadius);
        goalAngle = ang;
//        SmartDashboard.putNumber("goalAngle", goalAngle);
        goalDistance = Math.abs(goalAngle) * Math.abs(curRadius);
//        SmartDashboard.putNumber("goalDistance ---------- ", goalDistance);
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

  /*      SmartDashboard.putNumber("goalAngle", goalAngle);
        SmartDashboard.putNumber("curAngle", curAngle);
        SmartDashboard.putBoolean("isInAngleThresh", isAngleInThreshold);
        SmartDashboard.putBoolean("isInDistThresh", isDistInThreshold);
        SmartDashboard.putNumber("curPosDiff ", curPosDiff);
        SmartDashboard.putNumber("curAngleDiff ", curAngleDiff);
*/
        if (isAngleInThreshold) {
            prevAngle = curAngle;
            System.out.println("goalDistance" + goalDistance);
            System.out.println("pos" + curPos);
            System.out.println("angPos" + curAngle);
            isFinished = true;
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

        leftErr = leftVelocity - Robot.drive.getLeftVelocity();
        rightErr = rightVelocity - Robot.drive.getRightVelocity();

  /*      SmartDashboard.putNumber("turing value", getTurningValue());
        SmartDashboard.putNumber("desLeftVel", leftVelocity);
        SmartDashboard.putNumber("desRightVel", rightVelocity);
        SmartDashboard.putNumber("curDistance", curPos);
        SmartDashboard.putNumber("goalDistance", goalDistance);
        SmartDashboard.putNumber("curRadius", curRadius);
*/
        driveSignal = new DriveSignal(leftVelocity, rightVelocity);
        return driveSignal;
    }

    private double getTurningValue() {
        return goalVelocity * ((Math.abs(curRadius) - (WHEELBASE / 2.0)) / (Math.abs(curRadius) + (WHEELBASE / 2.0)));
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    public void setDistanceErrorThreshold(double distanceErrorThreshold) {
        this.distanceErrorThreshold = distanceErrorThreshold;
    }

    public void setAngleErrorThreshold(double angleErrorThreshold) {
        this.angleErrorThreshold = angleErrorThreshold;
    }

    public double getLeftError() {
        return leftErr;
    }

    public double getRightError() {
        return rightErr;
    }

}



