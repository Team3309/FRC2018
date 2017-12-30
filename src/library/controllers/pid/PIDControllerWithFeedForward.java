package library.controllers.pid;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

/*
 * <p>Controller for PID with feed forward, primarily for flywheels
 * @author Chase Blagden
 * */
public class PIDControllerWithFeedForward extends PIDController {

	// feed forward constant
	private double kF;

	// current desired velocity
	private double aimVel = 0.0;

	/*
	 * @return
	 * 
	 * @param kP, kI, kD, kF
	 */
	public PIDControllerWithFeedForward(double kP, double kI, double kD,
			double kF) {
		super(kP, kI, kD);
		this.kF = kF;
	}

	/*
	 * @return
	 * 
	 * @param kP, kI, kD, kF
	 */
	public void setConstants(double kP, double kI, double kD, double kF) {
		super.setConstants(kP, kI, kD);
		this.kF = kF;
	}

	@Override
	public OutputSignal getOutputSignal(InputState input) {
		OutputSignal signal = new OutputSignal();
		double power = getOutputSignal(input).getMotor() + kF * aimVel;
		signal.setMotor(power);
		return signal;
	}

	@Override
	public void reset() {
		super.reset();
	}

	/*
	 * @return
	 * 
	 * @param aimVel
	 */
	public void setAimVel(double aimVel) {
		this.aimVel = aimVel;
	}

	/*
	 * @return aimVel
	 * 
	 * @param
	 */
	public double getAimVel() {
		return aimVel;
	}

	@Override
	public void sendToSmartDash() {
		super.sendToSmartDash();
	/*	NetworkTable table = NetworkTable.getTable(getSubsystemID());
		kF = table.getNumber("kF", 0.0);
		table.putNumber("kF", kF);*/
	}

}
