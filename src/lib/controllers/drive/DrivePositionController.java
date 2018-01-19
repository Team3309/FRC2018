package lib.controllers.drive;

import lib.controllers.Controller3;
import lib.controllers.interfaces.Finishable;
import lib.controllers.pid.PIDConstants;
import lib.controllers.pid.PIDPositionController;

public class DrivePositionController extends Controller3<DriveSignal, DriveState, Double, Double> implements Finishable {

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
    public DriveSignal update(DriveState driveState, Double goalPos, Double goalAngle) {
        DriveSignal driveSignal;
        double leftOutput = leftSideController.update(driveState.getLeftPos(), goalPos);
        double rightOutput = rightSideController.update(driveState.getRightPos(), goalPos);
        double turningOutput = angleController.update(driveState.getAngPos(), goalAngle);
        driveSignal = new DriveSignal(leftOutput + turningOutput, rightOutput - turningOutput);
        return driveSignal;
    }

    public DriveSignal update(DriveState driveState, Double goalPos) {
        return update(driveState, goalPos, 0.0);
    }

    @Override
    public boolean isFinished() {
        return leftSideController.isFinished() && rightSideController.isFinished() && angleController.isFinished();
    }
}
