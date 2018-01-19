package lib.controllers.pid;

import lib.controllers.Controller3;

/*
 * <p>PID Controller with add-ons for velocity and acceleration
 *
 * @author Chase Blagden
 */
public class PIDVelocityController extends Controller3<Double, Double, Double, Double> {

    private PIDPositionController pidPositionController;

    // constant for adjusting velocity
    private double vScalar;

    // constant for adjusting acceleration
    private double aScalar;

    public PIDVelocityController(PIDConstants pidConstants, double v) {
        pidPositionController = new PIDPositionController(pidConstants);
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

    public PIDPositionController getController() {
        return pidPositionController;
    }

}
