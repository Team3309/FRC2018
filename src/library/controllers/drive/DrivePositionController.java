package library.controllers.drive;

import org.usfirst.frc.team3309.robot.Sensors;

import library.controllers.Controller;
import library.controllers.pid.PIDPositionController;
import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

public class DrivePositionController extends Controller {

	private double goal;
	private double angle;
	private PIDPositionController linearController;
	private PIDPositionController angleController;

	public DrivePositionController(double goal) {
		linearController = new PIDPositionController(0.0, 0.0, 0.0);
		linearController.setTHRESHOLD(200);
		linearController.setTIME_TO_BE_COMPLETE_S(0.2);
		linearController.setIsCompletable(true);
		linearController.setSubsystemID(this.getSubsystemID());
		linearController.setName("linear");

		angleController = new PIDPositionController(0.0, 0.0, 0.0);
		angleController.setTHRESHOLD(200);
		angleController.setTIME_TO_BE_COMPLETE_S(0.2);
		angleController.setIsCompletable(true);
		angleController.setSubsystemID(this.getSubsystemID());
		angleController.setName("angular");

		this.goal = goal;
		this.angle = Sensors.getAngle();
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
		OutputSignal linearOutput = linearController.getOutputSignal(linearInput);
		OutputSignal angularOutput = angleController.getOutputSignal(angularInput);
		OutputSignal signal = new OutputSignal();
		signal.setLeftRightMotor(linearOutput.getMotor() + angularOutput.getMotor(),
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

	@Override
	public void reset() {
	}

	public void setConstants(double kP, double kI, double kD) {
		this.linearController.setConstants(kP, kI, kD);
	}

}
