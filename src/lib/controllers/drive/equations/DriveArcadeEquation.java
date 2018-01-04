package lib.controllers.drive.equations;

import lib.controllers.Controller;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;

/*
 * @author Chase Blagden <p> Basic format of the arcade drive equation
 */
public class DriveArcadeEquation extends Controller {

    private final double throttleDeadband = 0.04;
    private final double turnDeadband = 0.04;

    @Override
    public OutputSignal getOutputSignal(InputState input) {
        OutputSignal signal = new OutputSignal();
        double throttle = handleDeadband(input.getY(), throttleDeadband);
        double turn = handleDeadband(input.getX(), turnDeadband);
        signal.setLeftRightMotor(throttle + turn, throttle - turn);
        return signal;
    }

    @Override
    public boolean isCompleted() {
        return false;
    }

    public double handleDeadband(double val, double lim) {
        return Math.abs(val) < Math.abs(lim) ? 0.0 : val;
    }

}
