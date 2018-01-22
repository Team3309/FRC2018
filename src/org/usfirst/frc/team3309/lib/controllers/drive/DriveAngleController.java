package org.usfirst.frc.team3309.lib.controllers.drive;

import org.usfirst.frc.team3309.lib.controllers.Controller2;
import org.usfirst.frc.team3309.lib.controllers.interfaces.Finishable;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDController;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveSignal;

public class DriveAngleController extends Controller2<DriveSignal, Double, Double> implements Finishable {

    private boolean isClockwiseTurn = false;

    private PIDController angleController;

    public DriveAngleController(PIDConstants pidConstants) {
        angleController = new PIDController(pidConstants);
    }

    @Override
    public DriveSignal update(Double curAngPos, Double goalAngle) {
        DriveSignal driveSignal;
        double output = angleController.update(curAngPos, goalAngle);
        if (!isClockwiseTurn) {
            driveSignal = new DriveSignal(output, -output);
        } else {
            driveSignal = new DriveSignal(-output, output);
        }
        return driveSignal;
    }


    public void setIsClockwiseTurn(boolean isClockwiseTurn) {
        this.isClockwiseTurn = isClockwiseTurn;
    }

    @Override
    public boolean isFinished() {
        return angleController.isFinished();
    }
}
