package library.controllers.pid;

/**
 * @author Chase.Blagden
 *         <p>
 *         Basic postion based PID controller
 */
public class PIDPositionController extends PIDController {

	public PIDPositionController(double kP, double kI, double kD) {
		super(kP, kI, kD);
	}

	public PIDPositionController(double kP, double kI, double kD, double kILimit) {
		super(kP, kI, kD, kILimit);
	}

}
