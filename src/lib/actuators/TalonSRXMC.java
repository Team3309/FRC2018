package lib.actuators;

import com.ctre.CANTalon;

public class TalonSRXMC extends CANTalon {

	private TalonControlMode currentControlMode = TalonControlMode.PercentVbus;

	public TalonSRXMC(int port) {
		super(port);
	}

	@Override
	public void changeControlMode(TalonControlMode controlMode) {
		if (!controlMode.equals(currentControlMode)) {
			currentControlMode = controlMode;
			super.changeControlMode(controlMode);
		}
	}

}
