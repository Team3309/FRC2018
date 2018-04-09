package org.usfirst.frc.team3309.lib.controllers.drive;

import org.usfirst.frc.team3309.lib.controllers.Controller3;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveSignal;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveState;
import org.usfirst.frc.team3309.lib.controllers.interfaces.Finishable;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDController;

public class DrivePositionController extends Controller3<DriveSignal, DriveState, Double, Double> implements Finishable {

    private PIDController leftSideController;
    private PIDController rightSideController;
    private PIDController angleController;

    public DrivePositionController(PIDConstants pidConstantsLeft,
                                   PIDConstants pidConstantsRight, PIDConstants pidConstantsTurn) {
        leftSideController = new PIDController(pidConstantsLeft);
        rightSideController = new PIDController(pidConstantsRight);
        angleController = new PIDController(pidConstantsTurn);
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
