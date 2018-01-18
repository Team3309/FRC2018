package lib.controllers.drive;

import lib.controllers.interfaces.Finishable;
import lib.controllers.pid.PIDConstants;
import lib.controllers.pid.PIDPositionController;

public class DriveAngleController extends DriveController implements Finishable {

    private double goalAngle;
    private double curAngPos;
    private boolean isClockwiseTurn = false;

    private PIDPositionController angleController;

    public DriveAngleController(PIDConstants pidConstants) {
        angleController = new PIDPositionController(pidConstants);
    }

    @Override
    public void update() {
        double output = angleController.update(curAngPos, goalAngle);
        if (!isClockwiseTurn) {
            driveSignal = new DriveSignal(output, -output);
        } else {
            driveSignal = new DriveSignal(-output, output);
        }
    }

    public DriveSignal update(double angPos, double goalAngle) {
        this.curAngPos = angPos;
        this.goalAngle = goalAngle;
        update();
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
