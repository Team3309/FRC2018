package lib.controllers.drive;

import lib.controllers.Controller;
import lib.controllers.pid.PIDPositionController;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;
import org.usfirst.frc.team3309.robot.Robot;

public class DrivePositionController extends Controller {

	private double goal;
	private double angle;
	private PIDPositionController linearController;
	private PIDPositionController angleController;

	public DrivePositionController(double goal) {
		linearController = new PIDPositionController(0.1, 0.0, 0.0);
		linearController.setErrorThreshold(200);
		linearController.setTimeoutS(0.2);
		linearController.setIsCompletable(true);
		linearController.setName("linear");

		angleController = new PIDPositionController(0.01, 0.0, 0.0);
		angleController.setErrorThreshold(200);
		angleController.setTimeoutS(0.2);
		angleController.setIsCompletable(true);
		angleController.setName("angular");

		this.goal = goal;
		this.angle = Robot.drive.getAngPos();
	}

	public DrivePositionController(double goal, double angle) {
		this(goal);
		this.angle = angle;
	}

	@Override
	public OutputSignal getOutputSignal(InputState input) {
		InputState linearInput = new InputState();
		InputState angularInput = new InputState();
		linearInput.setError(goal - input.getPos());
		angularInput.setError(angle - input.getAngPos());
		OutputSignal linearOutput = linearController
				.getOutputSignal(linearInput);
		OutputSignal angularOutput = angleController
				.getOutputSignal(angularInput);
		OutputSignal signal = new OutputSignal();
		signal.setLeftRightMotor(
				linearOutput.getMotor() + angularOutput.getMotor(),
				linearOutput.getMotor() - angularOutput.getMotor());
		return signal;
	}

	@Override
	public boolean isCompleted() {
		return linearController.isCompleted() && angleController.isCompleted();
	}

	@Override
	public void sendToSmartDash() {
		linearController.sendToSmartDash();
		angleController.sendToSmartDash();
	}

	public void setConstants(double kP, double kI, double kD) {
		this.linearController.setConstants(kP, kI, kD);
	}

}
