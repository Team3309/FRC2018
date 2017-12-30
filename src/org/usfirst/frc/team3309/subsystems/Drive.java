package org.usfirst.frc.team3309.subsystems;

import library.actuators.TalonSRXMC;

import org.usfirst.frc.team3309.commands.drive.DriveTeleop;
import org.usfirst.frc.team3309.robot.RobotMap;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	
	private TalonSRXMC left0 = new TalonSRXMC(RobotMap.DRIVE_LEFT_0_ID);
	private TalonSRXMC left1 = new TalonSRXMC(RobotMap.DRIVE_LEFT_1_ID);
	private TalonSRXMC right0 = new TalonSRXMC(RobotMap.DRIVE_RIGHT_0_ID);
	private TalonSRXMC right1 = new TalonSRXMC(RobotMap.DRIVE_RIGHT_1_ID);
	private Solenoid shifter = new Solenoid(RobotMap.SHIFTER);
	
	private final double ticksToDist = RobotMap.WHEEL_RADIUS_INCHES * Math.PI;
	
	public Drive() {
		enableBrakeMode(false);
		left0.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		right0.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveTeleop());
	}
	
	public double getInchesDistance() {
		return getAverageEncoder() * ticksToDist;
	}
	
	public double getAverageEncoder() {
		return (getLeftEncoder() + getRightEncoder()) / 2;
	}
	
	public double getLeftEncoder() {
		return left0.getPosition();
	}
	
	public double getRightEncoder() {
		return right0.getPosition();
	}
	
	public double getAverageVelocity() {
		return (getLeftVelocity() + getRightVelocity()) / 2;
	}
	
	public double getLeftVelocity() {
		return left0.getAnalogInVelocity();
	}
	
	public double getRightVelocity() {
		return right0.getAnalogInVelocity();
	}
	
	public void changeToPercentMode() {
		left0.changeControlMode(TalonControlMode.PercentVbus);
		left1.changeControlMode(TalonControlMode.PercentVbus);
		right0.changeControlMode(TalonControlMode.PercentVbus);
		right1.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	public void changeToVelocityMode() {
		left0.changeControlMode(TalonControlMode.Speed);
		left1.changeControlMode(TalonControlMode.Follower);
		left1.set(left0.getDeviceID());
		right0.changeControlMode(TalonControlMode.Speed);
		right1.changeControlMode(TalonControlMode.Follower);
		right1.set(right0.getDeviceID());
	}
	
	public void enableBrakeMode(boolean on) {
		left0.enableBrakeMode(on);
		left1.enableBrakeMode(on);
		right0.enableBrakeMode(on);
		right1.enableBrakeMode(on);
	}
	
	public void setVoltageRampRate(double rampRate) {
		left0.setVoltageRampRate(rampRate);
		left1.setVoltageRampRate(rampRate);
		right0.setVoltageRampRate(rampRate);
		right1.setVoltageRampRate(rampRate);
	}
	
	public void stopDrive() {
		setLeftRight(0, 0);
	}
	
	public void setLeftRight(double left, double right) {
		setLeft(left);
		setRight(right);
	}
	
	public void setLeft(double power) {
		left0.set(power);
		left1.set(power);
	}
	
	public void setRight(double power) {
		right0.set(power);
		right1.set(power);
	}
	
	public void setHighGear() {
		shifter.set(true);
	}
	
	public void setLowGear() {
		shifter.set(false);
	}

}
