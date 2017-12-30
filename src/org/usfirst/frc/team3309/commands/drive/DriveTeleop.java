package org.usfirst.frc.team3309.commands.drive;

import lib.controllers.drive.equations.DriveCheezyDriveEquation;
import lib.controllers.statesandsignals.InputState;
import lib.controllers.statesandsignals.OutputSignal;

import org.usfirst.frc.team3309.commands.ControlledCommand;
import org.usfirst.frc.team3309.driverstation.Controls;
import org.usfirst.frc.team3309.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

public class DriveTeleop extends ControlledCommand {

	public DriveTeleop() {
		super("DriveTeleop");
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		Robot.drive.changeToPercentMode();
	}

	@Override
	protected void execute() {
		this.setController(new DriveCheezyDriveEquation());
		OutputSignal signal = this.getController().getOutputSignal(
				getInputState());
		Robot.drive.setLeftRight(signal.getLeftMotor(), signal.getRightMotor());
		if (Controls.driverRemote.getBumper(Hand.kLeft)) {
			Robot.drive.setLowGear();
		} else {
			Robot.drive.setHighGear();
		}
		this.sendToDashboard();
	}

	@Override
	protected InputState getInputState() {
		InputState state = new InputState();
		state.setX(Controls.driverRemote.getY(Hand.kLeft));
		state.setY(Controls.driverRemote.getX(Hand.kRight));
		state.setIsTrue(Controls.driverRemote.getBumper(Hand.kRight));
		return state;
	}

	@Override
	protected void interrupted() {
		Robot.drive.stopDrive();
	}

}
