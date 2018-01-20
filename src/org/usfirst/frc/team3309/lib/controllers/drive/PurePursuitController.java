package org.usfirst.frc.team3309.lib.controllers.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.lib.controllers.Controller1;
import org.usfirst.frc.team3309.lib.controllers.interfaces.Finishable;
import org.usfirst.frc.team3309.robot.Robot;

import java.util.ArrayList;

/*
* TODO: Make goalVelocity member of the ArrayList<Waypoint> path, instead of constant value for the whole path.
* */
public class PurePursuitController extends Controller1<DriveSignal, DriveState> implements Finishable {

    private final ArrayList<Waypoint> path;

    private double goalDistance;
    private double curRadius;
    private double goalAngle;

    private int curPathIndex = 0;

    private final double goalVelocity = 0.5;
    private final double WHEELBASE;

    public PurePursuitController(ArrayList<Waypoint> path, double WHEELBASE) {
        this.path = path;
        this.WHEELBASE = WHEELBASE;
        curRadius = this.path.get(0).radius;
        goalAngle = this.path.get(0).angle;
        goalDistance += Math.abs(goalAngle) * curRadius;
    }

    @Override
    public DriveSignal update(DriveState driveState) {
        DriveSignal driveSignal;
        double leftVelocity;
        double rightVelocity;

        double curPos = driveState.getAveragePos();
        double curAngle = driveState.getAngPos();

        if (curPos > goalDistance) {
            curPathIndex++;
            System.out.println("curPathIndex" + curPathIndex);
            System.out.println("goalDistance" + goalDistance);
            System.out.println("pos" + curPos);
            System.out.println("angPos" + curAngle);
            System.out.println("goalAngle" + goalAngle);
            if (isFinished()) {
                driveSignal = new DriveSignal(0, 0);
                return driveSignal;
            } else {
                goalAngle = path.get(curPathIndex).angle;
                curRadius = path.get(curPathIndex).radius;
                goalDistance += Math.abs(goalAngle) * curRadius;
            }
        }

        if (goalAngle < 0) {
            leftVelocity = getTurningValue();
            rightVelocity = goalVelocity;
        } else if (goalAngle > 0) {
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

        driveSignal = new DriveSignal(leftVelocity, rightVelocity);
        return driveSignal;
    }

    private double getTurningValue() {
        return goalVelocity * ((curRadius - (WHEELBASE / 2.0)) / (curRadius + (WHEELBASE / 2.0)));
    }

    @Override
    public boolean isFinished() {
        return curPathIndex >= path.size();
    }

}
