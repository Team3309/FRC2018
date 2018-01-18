package lib.controllers.drive;

import lib.controllers.interfaces.Finishable;
import lib.controllers.pid.PIDConstants;
import lib.controllers.pid.PIDPositionController;

public class DrivePositionController extends DriveController implements Finishable {

    private double goalPos;
    private double goalAngle;

    private double leftPos;
    private double rightPos;
    private double curAngle;

    private PIDPositionController leftSideController;
    private PIDPositionController rightSideController;
    private PIDPositionController angleController;

    public DrivePositionController(PIDConstants pidConstantsLeft,
                                   PIDConstants pidConstantsRight, PIDConstants pidConstantsTurn) {
        leftSideController = new PIDPositionController(pidConstantsLeft);
        rightSideController = new PIDPositionController(pidConstantsRight);
        angleController = new PIDPositionController(pidConstantsTurn);
    }

    public DrivePositionController(PIDConstants pidConstantsLinear, PIDConstants pidConstantsTurn) {
        this(pidConstantsLinear, pidConstantsLinear, pidConstantsTurn);
    }

    @Override
    public void update() {
        double leftOutput = leftSideController.update(leftPos, goalPos);
        double rightOutput = rightSideController.update(rightPos, goalPos);
        double turningOutput = angleController.update(curAngle, goalAngle);
        driveSignal = new DriveSignal(leftOutput + turningOutput, rightOutput - turningOutput);
    }

    public DriveSignal update(double leftPos, double rightPos, double goalPos, double curAngle, double goalAngle) {
        this.leftPos = leftPos;
        this.rightPos = rightPos;
        this.curAngle = curAngle;
        this.goalPos = goalPos;
        this.goalAngle = goalAngle;
        update();
        return driveSignal;
    }

    public DriveSignal update(double curPos, double goalPos, double curAngle, double goalAngle) {
        update(curPos, curPos, goalPos, curAngle, goalAngle);
        return driveSignal;
    }

    @Override
    public boolean isFinished() {
        return leftSideController.isFinished() && rightSideController.isFinished() && angleController.isFinished();
    }
}
