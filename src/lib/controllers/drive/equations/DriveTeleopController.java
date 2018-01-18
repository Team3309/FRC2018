package lib.controllers.drive.equations;

import lib.controllers.interfaces.Finishable;
import lib.controllers.drive.DriveController;

public abstract class DriveTeleopController extends DriveController implements Finishable {

    protected double throttle;
    protected double turn;

    protected double throttleDeadband = 0.04;
    protected double turnDeadband = 0.04;

    @Override
    public boolean isFinished() {
        return false;
    }

    protected double handleDeadband(double val, double lim) {
        return Math.abs(val) < Math.abs(lim) ? 0.0 : val;
    }

    public void setThrottleDeadband(double throttleDeadband) {
        this.throttleDeadband = throttleDeadband;
    }

    public void setTurnDeadband(double turnDeadband) {
        this.turnDeadband = turnDeadband;
    }

    public void setThrottleAndTurn(double throttle, double turn) {
        this.throttle = throttle;
        this.turn = turn;
    }

}
