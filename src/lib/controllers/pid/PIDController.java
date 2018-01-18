package lib.controllers.pid;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import lib.ChaseTimer;
import lib.controllers.Controller;
import lib.controllers.Finishable;
import lib.controllers.Resettable;

/*
 * <p>Structure of PID Controller See
 * http://www.inpharmix.com/jps/PID_Controller_For_Lego_Mindstorms_Robots.html
 * for understanding of math
 * 
 * @author Chase Blagden
 */
public abstract class PIDController extends Controller implements Resettable, Finishable {

	public double pScalar;
	public double iScalar;
	public double dScalar;

	// limit for integral value
	public double integralLimit;

	private double totalIntegral = 0.0;
	private double prevError;

	private boolean isUseDashboard = false;

	// threshold of error
	private double errorThreshold = 30;

	// time (s) in which controller must be within threshold
	private double timeoutS = .250;

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
	}

	@Override
	public void update() {

		double error = setpoint - current;

		// checking for integral being over limit
		if (totalIntegral * iScalar > integralLimit) {
			totalIntegral = integralLimit / iScalar;
		} else if (totalIntegral * iScalar < integralLimit) {
			totalIntegral = -integralLimit / iScalar;
		}

		// controls ramp up
		double proportional = pScalar * error;

		// makes up for steady state error
		totalIntegral = iScalar * (totalIntegral + error);

		// dampens oscillation
		double derivative = dScalar * (prevError - error);

		output = proportional + totalIntegral + derivative;

		prevError = error;
	}

	public double update(double current, double setpoint) {
	    setCurrentAndSetpoint(current, setpoint);
	    update();
	    return output;
    }


	/*
	 * @return
	 * 
	 * @param isUseSmartDash
	 */
	public void setIsUseDashboard(boolean isUseSmartDash) {
		this.isUseDashboard = isUseSmartDash;
	}

	/*
	 * @return
	 * 
	 * @param pScalar, iScalar, dScalar
	 */
	public void setConstants(double kP, double kI, double kD) {
		this.pScalar = kP;
		this.iScalar = kI;
		this.dScalar = kD;
	}

	public void setConstants(double kP, double kI, double kD, double kILimit) {
		setConstants(kP, kI, kD);
		this.integralLimit = kILimit;
	}

	@Override
	public void reset() {
		this.prevError = 0.0;
		this.totalIntegral = 0.0;
	}

	/*
	 * @return
	 * 
	 * @param isCompletable for setting controller to terminate
	 */
	public void setIsCompletable(boolean isCompletable) {
		this.isCompletable = isCompletable;
	}

	/*
	 * @return isCompletable
	 * 
	 * @param
	 */
	public boolean getIsCompletable() {
		return this.isCompletable;
	}

	@Override
	public boolean isFinished() {
		if (isCompletable) {
			if (doneTimer == null) {
				doneTimer = new ChaseTimer(timeoutS);
			}
			return this.doneTimer
					.isConditionMaintained(Math.abs(prevError) < errorThreshold);
		}
		return false;
	}

	/*
	 * @return
	 * 
	 * @param THRESHOLD of error
	 */
	public void setErrorThreshold(double errorThreshold) {
		this.errorThreshold = errorThreshold;
	}

	/*
	 * @return THRESHOLD of error
	 */
	public double getErrorThreshold() {
		return this.errorThreshold;
	}

	/*
	 * @return
	 * 
	 * @param TIME_TO_BE_COMPLETE_MS time in which controller must be within
	 * THRESHOLD to terminate
	 */
	public void setTimeoutSec(double timeoutS) {
		this.timeoutS = timeoutS;
	}

	/*
	 * @return TIME_TO_BE_COMPLETE_MS time in which controller must be within
	 * THRESHOLD to terminate
	 * 
	 * @param
	 */
	public double getTimeoutS() {
		return this.timeoutS;
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
