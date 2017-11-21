package library.controllers.pid;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import library.controllers.Controller;
import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

public abstract class PIDController extends Controller {

	private double kP;
	private double kI;
	private double kD;
	
	private double pastIntegral = 0.0;
	private double pastError;
	
	private boolean isUseSmartDash = false;
	
	public PIDController(double kP, double kI, double kD) {
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
	}
	
	@Override
	public OutputSignal getOutputSignal(InputState input) {
		OutputSignal output = new OutputSignal();
		double error = input.getError();
		double proportional = kP * error;
		double integral = kI * (pastIntegral + error);
		double derivative = kD * (error - pastError);
		output.setMotor(proportional + integral + derivative);
		return output;
	}
	
	public void setIsUseSmartDash(boolean use) {
		this.isUseSmartDash = use;
	}
	
	public void setConstants(double kP, double kI, double kD) {
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
	}
	
	@Override
	public void reset() {
		this.pastError = 0.0;
		this.pastIntegral = 0.0;
	}
	
	@Override
	public void sendToSmartDash() {
		if (this.isUseSmartDash){}		
	}
	
}
