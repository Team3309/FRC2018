package lib.controllers.drive.equations;

import lib.LibMath;
import lib.controllers.Controller2;
import lib.controllers.drive.DriveSignal;

/*
 * @author Chase Blagden <p> Basic format of the arcade drive equation
 */
public class DriveArcadeEquation extends Controller2<DriveSignal, Double, Double> {

    private double throttleDeadband = 0.04;
    private double turnDeadband = 0.04;

    public DriveArcadeEquation(double throttleDeadband, double turnDeadband) {
        this.throttleDeadband = throttleDeadband;
        this.turnDeadband = turnDeadband;
    }

    public DriveArcadeEquation() {}

    public DriveSignal update(Double throttle, Double turn) {
        DriveSignal driveSignal;
        double linearPower = LibMath.handleDeadband(throttle, throttleDeadband);
        double angPower = LibMath.handleDeadband(turn, turnDeadband);
        driveSignal = new DriveSignal(linearPower + angPower, linearPower - angPower);
        return driveSignal;
    }

}
