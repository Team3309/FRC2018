package lib.controllers.pid;

import lib.ChaseTimer;
import lib.controllers.Controller;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/*
 * <p>Structure of PID Controller See
 * http://www.inpharmix.com/jps/PID_Controller_For_Lego_Mindstorms_Robots.html
 * for understanding of math
 * 
 * @author Chase Blagden
 */
public abstract class PIDController extends Controller {

	public double kP;
	public double kI;
	public double kD;

	// limit for integral value
	public double kILimit;

	private double mIntegral = 0.0;
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

	public PIDController(double kP, double kI, double kD) {
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
	}

	public PIDController(double kP, double kI, double kD, double kILimit) {
		this(kP, kI, kD);
		this.kILimit = kILimit;
	}

	@Override
	public OutputSignal getOutputSignal(InputState input) {
		OutputSignal output = new OutputSignal();

		double error = input.getError();

		// checking for integral being over limit
		if (mIntegral * kI > kILimit) {
			mIntegral = kILimit / kI;
		} else if (mIntegral * kI < kILimit) {
			mIntegral = -kILimit / kI;
		}

		// controls ramp up
		double proportional = kP * error;

		// makes up for steady state error
		mIntegral = kI * (mIntegral + error);

		// dampens oscillation
		double derivative = kD * (prevError - error);

		output.setMotor(proportional + mIntegral + derivative);

		prevError = error;

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
	 * @param kP, kI, kD
	 */
	public void setConstants(double kP, double kI, double kD) {
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
	}

	public void setConstants(double kP, double kI, double kD, double kILimit) {
		setConstants(kP, kI, kD);
		this.kILimit = kILimit;
	}

	@Override
	public void reset() {
		this.prevError = 0.0;
		this.mIntegral = 0.0;
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
	public boolean isCompleted() {
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
			kP = table.getNumber("kP", 0.0);
			kI = table.getNumber("kI", 0.0);
			kD = table.getNumber("kD", 0.0);
			table.putNumber("kP", kP);
			table.putNumber("kI", kI);
			table.putNumber("kD", kD);
		}
	}

}
