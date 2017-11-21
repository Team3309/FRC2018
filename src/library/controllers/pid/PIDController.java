package library.controllers.pid;

import library.ChaseTimer;
import library.controllers.Controller;
import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/*
 * Structure of PID Controller
 * See http://www.inpharmix.com/jps/PID_Controller_For_Lego_Mindstorms_Robots.html 
 * for understanding of math
 * @author Chase Blagden
 * */
public abstract class PIDController extends Controller {

	public double kP;
	public double kI;
	public double kD;

	// limit for integral value
	public double kILimit;

	private double mIntegral = 0.0;
	private double prevError;

	private boolean isUseSmartDash = false;

	// threshold of error
	private double THRESHOLD = 30;

	// time (ms) in which controller must be within threshold
	private double TIME_TO_BE_COMPLETE_MS = 0.25;

	// set whether controller can be finished
	private boolean isCompletable;

	// timer for checking time passed
	private ChaseTimer doneTimer = new ChaseTimer();

	public PIDController(double kP, double kI, double kD) {
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		NetworkTable.getTable(getSubsystemID()).putNumber("kP", kP);
		NetworkTable.getTable(getSubsystemID()).putNumber("kI", kI);
		NetworkTable.getTable(getSubsystemID()).putNumber("kD", kD);
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
		double derivative = kD * (error - prevError);

		output.setMotor(proportional + mIntegral + derivative);

		prevError = error;

		return output;
	}

	/*
	 * @return
	 * 
	 * @param isUseSmartDash
	 */
	public void setIsUseSmartDash(boolean isUseSmartDash) {
		this.isUseSmartDash = isUseSmartDash;
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
	public boolean isCompletable() {
		if (isCompletable) {
			return this.doneTimer
					.isConditionMaintained(Math.abs(prevError) < THRESHOLD);
		}
		return false;
	}

	/*
	 * @return
	 * 
	 * @param THRESHOLD of error
	 */
	public void setTHRESHOLD(double THRESHOLD) {
		this.THRESHOLD = THRESHOLD;
	}

	/*
	 * @return THRESHOLD of error
	 */
	public double getTHRESHOLD() {
		return this.THRESHOLD;
	}

	/*
	 * @return
	 * 
	 * @param TIME_TO_BE_COMPLETE_MS time in which controller must be within
	 * THRESHOLD to terminate
	 */
	public void setTIME_TO_BE_COMPLETE_MS(double TIME_TO_BE_COMPLETE_MS) {
		this.TIME_TO_BE_COMPLETE_MS = TIME_TO_BE_COMPLETE_MS;
	}

	/*
	 * @return TIME_TO_BE_COMPLETE_MS time in which controller must be within
	 * THRESHOLD to terminate
	 * 
	 * @param
	 */
	public double getTIME_TO_BE_COMPLETE_MS() {
		return this.TIME_TO_BE_COMPLETE_MS;
	}

	@Override
	public void sendToSmartDash() {
		if (this.isUseSmartDash) {
			NetworkTable table = NetworkTable.getTable(getSubsystemID());
			kP = table.getNumber("kP", 0.0);
			kI = table.getNumber("kI", 0.0);
			kD = table.getNumber("kD", 0.0);
			table.putNumber("kP", kP);
			table.putNumber("kI", kI);
			table.putNumber("kD", kD);
		}
	}

}
