package lib.controllers.pid;

/*
 * <p>PID Controller with add-ons for velocity and acceleration
 * 
 * @author Chase Blagden
 */
public class PIDVelocityController extends PIDController {

	// constant for adjusting velocity
	private double vScalar;

	// constant for adjusting acceleration
	private double aScalar;

	// desired velocity
	private double aimVel = 0.0;

	// desired acceleration
	private double aimAcc = 0.0;
	
	public PIDVelocityController(PIDConstants pidConstants, double v) {
		super(pidConstants);
		this.vScalar = vScalar;
	}

	public PIDVelocityController(PIDConstants pidConstants, double v, double a) {
		this(pidConstants, v);
		this.aScalar = a;
	}

	public void setConstants(double kP, double kI, double kD, double kV,
			double kA) {
		super.setConstants(kP, kI, kD);
		this.vScalar = kV;
		this.aScalar = kA;
	}

	@Override
	public void update() {
		output = super.update(current, output) + vScalar * aimVel + aScalar * aimAcc;
	}

	public double update(double current, double setpoint) {
		setCurrentAndSetpoint(current, setpoint);
		update();
		return output;
	}

}
