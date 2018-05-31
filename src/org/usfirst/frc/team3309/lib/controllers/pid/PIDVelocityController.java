package org.usfirst.frc.team3309.lib.controllers.pid;

import org.usfirst.frc.team3309.lib.controllers.Controller3;

/*
 * <p>PID Controller with add-ons for velocity and acceleration
 *
 * @author Chase Blagden
 */
public class PIDVelocityController extends Controller3<Double, Double, Double, Double> {

    private org.usfirst.frc.team3309.lib.controllers.pid.PIDController pidPositionController;

    // constant for adjusting velocity
    private double vScalar;

    // constant for adjusting acceleration
    private double aScalar;

    public PIDVelocityController(PIDConstants pidConstants, double v) {
        pidPositionController = new org.usfirst.frc.team3309.lib.controllers.pid.PIDController(pidConstants);
        this.vScalar = v;
    }

    public PIDVelocityController(PIDConstants pidConstants, double v, double a) {
        this(pidConstants, v);
        this.aScalar = a;
    }

    public void setConstants(PIDConstants pidConstants, double v,
                             double a) {
        pidPositionController.setConstants(pidConstants);
        this.vScalar = v;
        this.aScalar = a;
    }

    @Override
    public Double update(Double current, Double aimVel, Double aimAcc) {
        return pidPositionController.update(current, aimVel) + vScalar * aimVel + aScalar * aimAcc;
    }

    public PIDController getController() {
        return pidPositionController;
    }

}
