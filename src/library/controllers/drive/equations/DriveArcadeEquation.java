package library.controllers.drive.equations;

import org.usfirst.frc.team3309.driverstation.Controls;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import library.controllers.Controller;
import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

public class DriveArcadeEquation extends Controller {

	@Override
	public boolean isCompleted() {
		return false;
	}

	@Override
	public OutputSignal getOutputSignal(InputState input) {
		OutputSignal signal = new OutputSignal();
		double throttle = Controls.driverRemote.getY(Hand.kLeft);
		double turn = Controls.driverRemote.getX(Hand.kRight);
		signal.setLeftRightMotor(throttle + turn, throttle - turn);
		return signal;
	}

	@Override
	public void reset() { }

}
