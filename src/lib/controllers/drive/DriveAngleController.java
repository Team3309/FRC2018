package lib.controllers.drive;

import lib.controllers.pid.PIDController;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;

public class DriveAngleController extends PIDController {

	private final double goalAngle;
	private final double errorThreshold = 10;
	private final double timeoutS = 0.2;

	public DriveAngleController(double goalAngle) {
		super(0.0, 0.0, 0.0);
		this.goalAngle = goalAngle;
		setErrorThreshold(errorThreshold);
		setTimeoutSec(timeoutS);
		setIsCompletable(true);
		setName("DriveAngleController");
	}

	@Override
	public OutputSignal getOutputSignal(InputState inputState) {
		double error = goalAngle - inputState.getAngPos();
		InputState input = new InputState();
		input.setError(error);
		OutputSignal x = new OutputSignal();
		x.setLeftMotor(-getOutputSignal(input).getMotor());
		x.setRightMotor(getOutputSignal(input).getMotor());
		return x;
	}

}
