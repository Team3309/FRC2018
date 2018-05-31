package org.usfirst.frc.team3309.lib.controllers.drive;

import org.usfirst.frc.team3309.lib.controllers.Controller2;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveSignal;
import org.usfirst.frc.team3309.lib.math.LibMath;

/*
 * @author Chase Blagden <p> Basic format of the arcade drive equation
 */
public class DriveArcadeController extends Controller2<DriveSignal, Double, Double> {

    private double throttleDeadband = 0.04;
    private double turnDeadband = 0.04;

    public DriveArcadeController(double throttleDeadband, double turnDeadband) {
        this.throttleDeadband = throttleDeadband;
        this.turnDeadband = turnDeadband;
    }

    public DriveArcadeController() {}

    public DriveSignal update(Double throttle, Double turn) {
        DriveSignal driveSignal;
        double linearPower = LibMath.handleDeadband(throttle, throttleDeadband);
        double angPower = LibMath.handleDeadband(turn, turnDeadband);
        driveSignal = new DriveSignal(linearPower + angPower, linearPower - angPower);
        return driveSignal;
    }

}
