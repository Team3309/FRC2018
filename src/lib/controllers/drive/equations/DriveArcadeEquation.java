package lib.controllers.drive.equations;

import lib.controllers.drive.DriveSignal;

/*
 * @author Chase Blagden <p> Basic format of the arcade drive equation
 */
public class DriveArcadeEquation extends DriveTeleopController {

    @Override
    public void update() {
        double linearPower = handleDeadband(throttle, throttleDeadband);
        double angPower = handleDeadband(turn, turnDeadband);
        driveSignal = new DriveSignal(linearPower + angPower, linearPower - angPower);
    }

    public DriveSignal update(double throttle, double turn) {
        setThrottleAndTurn(throttle, turn);
        update();
        return driveSignal;
    }

}
