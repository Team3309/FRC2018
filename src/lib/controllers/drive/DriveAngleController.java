package lib.controllers.drive;

import lib.controllers.Controller2;
import lib.controllers.interfaces.Finishable;
import lib.controllers.pid.PIDConstants;
import lib.controllers.pid.PIDPositionController;

public class DriveAngleController extends Controller2<DriveSignal, Double, Double> implements Finishable {

    private boolean isClockwiseTurn = false;

    private PIDPositionController angleController;

    public DriveAngleController(PIDConstants pidConstants) {
        angleController = new PIDPositionController(pidConstants);
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
