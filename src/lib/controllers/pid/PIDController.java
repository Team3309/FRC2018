package lib.controllers.pid;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import lib.ChaseTimer;
import lib.controllers.Controller;
import lib.controllers.interfaces.Finishable;
import lib.controllers.interfaces.Resettable;

/*
 * <p>Structure of PID Controller See
 * http://www.inpharmix.com/jps/PID_Controller_For_Lego_Mindstorms_Robots.html
 * for understanding of math
 *
 * @author Chase Blagden
 */
public abstract class PIDController extends Controller implements Resettable, Finishable {

    protected double pScalar;
    protected double iScalar;
    protected double dScalar;

    private PIDConstants pidConstants;

    // limit for integral value
    public double integralLimit;

    private double totalIntegral = 0.0;
    private double prevError;

    private boolean isUseDashboard = false;

    // threshold of error
    private double errorThreshold = 30;

    // time (s) in which controller must be within threshold
    private double timeoutSec = .250;

    // set whether controller can be finished
    private boolean isCompletable;

    // timer for checking time passed
    private ChaseTimer doneTimer;

    protected double setpoint;
    protected double current;
    protected double output;

    public PIDController(PIDConstants pidConstants) {
        this.pScalar = pidConstants.getP();
        this.iScalar = pidConstants.getI();
        this.dScalar = pidConstants.getD();
        this.integralLimit = pidConstants.getIntegralLimit();
        this.pidConstants = pidConstants;
    }

    @Override
    public void update() {

        double error = setpoint - current;

        // checking for integral being over limit
        double integralLimit = pidConstants.getIntegralLimit();
        if (totalIntegral * iScalar > integralLimit) {
            totalIntegral = integralLimit / iScalar;
        } else if (totalIntegral * iScalar < integralLimit) {
            totalIntegral = -integralLimit / iScalar;
        }

        // controls ramp up
        double proportional = pidConstants.getP() * error;

        // makes up for steady state error
        totalIntegral = pidConstants.getI() * (totalIntegral + error);

        // dampens oscillation
        double derivative = pidConstants.getD() * (prevError - error);

        output = proportional + totalIntegral + derivative;

        prevError = error;
    }

    public double update(double current, double setpoint) {
        setCurrentAndSetpoint(current, setpoint);
        update();
        return output;
    }

    public void setIsUseDashboard(boolean isUseSmartDash) {
        this.isUseDashboard = isUseSmartDash;
    }

    public void setConstants(double p, double i, double d) {
        pidConstants.setConstants(p, i, d);
    }

    public void setConstants(double p, double i, double d, double integralLimit) {
        setConstants(p, i, d);
        this.integralLimit = integralLimit;
    }

    @Override
    public void reset() {
        this.prevError = 0.0;
        this.totalIntegral = 0.0;
    }

    /*
     * @param isCompletable for setting controller to terminate
     */
    public void setIsCompletable(boolean isCompletable) {
        this.isCompletable = isCompletable;
    }

    public boolean getIsCompletable() {
        return this.isCompletable;
    }

    @Override
    public boolean isFinished() {
        if (isCompletable) {
            if (doneTimer == null) {
                doneTimer = new ChaseTimer(timeoutSec);
            }
            return this.doneTimer
                    .isConditionMaintained(Math.abs(prevError) < errorThreshold);
        }
        return false;
    }

    public void setErrorThreshold(double errorThreshold) {
        this.errorThreshold = errorThreshold;
    }


    public double getErrorThreshold() {
        return this.errorThreshold;
    }

    public void setTimeoutSec(double timeoutS) {
        this.timeoutSec = timeoutS;
    }

    public double getTimeoutSec() {
        return this.timeoutSec;
    }

    @Override
    public void sendToDashboard() {
        if (this.isUseDashboard) {
            NetworkTable table = NetworkTable.getTable("");
            pScalar = table.getNumber("pScalar", 0.0);
            iScalar = table.getNumber("iScalar", 0.0);
            dScalar = table.getNumber("dScalar", 0.0);
            table.putNumber("pScalar", pScalar);
            table.putNumber("iScalar", iScalar);
            table.putNumber("dScalar", dScalar);
        }
    }

    public void setCurrentAndSetpoint(double current, double setpoint) {
        this.current = current;
        this.setpoint = setpoint;
    }

}
