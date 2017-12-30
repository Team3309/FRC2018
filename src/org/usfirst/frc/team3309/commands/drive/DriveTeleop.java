package org.usfirst.frc.team3309.commands.drive;

import library.controllers.drive.equations.DriveCheezyDriveEquation;
import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

import org.usfirst.frc.team3309.commands.ControlledCommand;
import org.usfirst.frc.team3309.driverstation.Controls;
import org.usfirst.frc.team3309.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

public class DriveTeleop extends ControlledCommand {

	public DriveTeleop() {
		requires(Robot.drive);
	}

	@Override
	public void initialize() {
		Robot.drive.changeToPercentMode();
	}

	@Override
	public void execute() {
		this.setController(new DriveCheezyDriveEquation());
		OutputSignal signal = this.getController().getOutputSignal(
				getInputState());
		Robot.drive.setLeftRight(signal.getLeftMotor(), signal.getRightMotor());
		if (Controls.driverRemote.getBumper(Hand.kLeft)) {
			Robot.drive.setLowGear();
		} else {
			Robot.drive.setHighGear();
		}
	}

	@Override
	public InputState getInputState() {
		InputState state = new InputState();
		state.setX(Controls.driverRemote.getY(Hand.kLeft));
		state.setY(Controls.driverRemote.getX(Hand.kRight));
		state.setIsTrue(Controls.driverRemote.getBumper(Hand.kRight));
		return state;
	}

	@Override
	public void interrupted() {
		Robot.drive.stopDrive();
	}

}
