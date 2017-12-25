package org.usfirst.team3309.subsystems;

import library.ControlledSubsystem;
import library.actuators.TalonSRXMC;
import library.controllers.BlankController;
import library.controllers.drive.equations.DriveCheezyDriveEquation;
import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

import org.usfirst.frc.team3309.driverstation.Controls;
import org.usfirst.frc.team3309.robot.RobotMap;
import org.usfirst.frc.team3309.robot.Sensors;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Drive extends ControlledSubsystem {

	private TalonSRXMC rightMaster = new TalonSRXMC(RobotMap.DRIVE_RIGHT_0_ID);
	private TalonSRXMC rightSlave0 = new TalonSRXMC(RobotMap.DRIVE_RIGHT_1_ID);
	private TalonSRXMC rightSlave1 = new TalonSRXMC(RobotMap.DRIVE_RIGHT_2_ID);
	private TalonSRXMC leftMaster = new TalonSRXMC(RobotMap.DRIVE_LEFT_0_ID);
	private TalonSRXMC leftSlave0 = new TalonSRXMC(RobotMap.DRIVE_LEFT_1_ID);
	private TalonSRXMC leftSlave1 = new TalonSRXMC(RobotMap.DRIVE_LEFT_2_ID);

	private NetworkTable table = NetworkTable.getTable("Drive");

	private Solenoid shifter = new Solenoid(RobotMap.SHIFTER);

	private static Drive instance;

	public static Drive getInstance() {
		if (instance == null) {
			instance = new Drive();
		}
		return instance;
	}

	private Drive() {
		super("Drive");
		rightMaster.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		leftMaster.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
	}

	@Override
	public void init() {
	}

	@Override
	public void initAuto() {
		this.setLowGear();
		Sensors.resetDrive();
		this.setController(new BlankController());
		this.setVoltageRampRate(0);
	}

	@Override
	public void initTeleop() {
		this.setController(new DriveCheezyDriveEquation());
		this.stopDrive();
		this.setVoltageRampRate(10);
	}

	@Override
	public void updateTeleop() {
		this.setController(new DriveCheezyDriveEquation());
		if (Controls.driverRemote.getBumper(Hand.kLeft)) {
			this.setHighGear();
		} else {
			this.setLowGear();
		}
		OutputSignal signal = this.getController().getOutputSignal(
				getInputState());
		setLeftRight(signal.getLeftMotor(), signal.getRightMotor());
	}

	@Override
	public void updateAuto() {
		this.changeToVelocityMode();
		OutputSignal signal = this.getController().getOutputSignal(
				getInputState());
		setLeftRight(signal.getLeftMotor(), signal.getRightMotor());
	}

	@Override
	public void manualControl() {
		updateTeleop();
	}

	@Override
	public InputState getInputState() {
		InputState input = new InputState();
		input.setPos(this.getDistanceTraveled());
		input.setAngPos(Sensors.getAngle());
		return input;
	}

	@Override
	public void sendToDashboard() {

	}

	public void changeToPercentMode() {
		rightMaster.changeControlMode(TalonControlMode.PercentVbus);
		rightSlave0.changeControlMode(TalonControlMode.PercentVbus);
		rightSlave1.changeControlMode(TalonControlMode.PercentVbus);
		leftMaster.changeControlMode(TalonControlMode.PercentVbus);
		leftSlave0.changeControlMode(TalonControlMode.PercentVbus);
		leftSlave1.changeControlMode(TalonControlMode.PercentVbus);
	}

	public void changeToVelocityMode() {
		rightMaster.changeControlMode(TalonControlMode.Speed);
		rightSlave0.changeControlMode(TalonControlMode.Follower);
		rightSlave1.changeControlMode(TalonControlMode.Follower);
		rightSlave0.set(rightMaster.getDeviceID());
		rightSlave1.set(rightMaster.getDeviceID());
		leftMaster.changeControlMode(TalonControlMode.Speed);
		leftSlave0.changeControlMode(TalonControlMode.Follower);
		leftSlave1.changeControlMode(TalonControlMode.Follower);
		leftSlave0.set(leftMaster.getDeviceID());
		leftSlave1.set(leftMaster.getDeviceID());
	}

	public void setLeftRight(double left, double right) {
		setLeft(left);
		setRight(right);
	}

	/*
	 * Sets left side of drive train
	 * 
	 * @param left rightMotorSpeed
	 */
	public void setLeft(double left) {
		if (this.leftMaster.getControlMode() == TalonControlMode.Speed) {
			this.leftMaster.set(-left);
		} else {
			this.leftMaster.set(left);
			this.leftSlave0.set(left);
			this.leftSlave1.set(left);
		}
	}

	/*
	 * Sets right side of drive train
	 * 
	 * @param right rightMotorSpeed
	 */
	public void setRight(double right) {
		if (this.rightMaster.getControlMode() == TalonControlMode.Speed) {
			this.rightMaster.set(-right);
		} else {
			this.rightMaster.set(right);
			this.rightSlave0.set(right);
			this.rightSlave1.set(right);
		}
	}

	/*
	 * Sets the voltage ramp rate
	 * 
	 * @param rate voltage ramp rate in percent per second
	 */
	public void setVoltageRampRate(double rate) {
		this.rightMaster.setVoltageRampRate(rate);
		this.rightSlave0.setVoltageRampRate(rate);
		this.rightSlave1.setVoltageRampRate(rate);
		this.leftMaster.setVoltageRampRate(rate);
		this.leftSlave0.setVoltageRampRate(rate);
		this.leftSlave1.setVoltageRampRate(rate);
	}

	public void stopDrive() {
		this.changeToPercentMode();
		this.setController(new BlankController());
		this.setLeftRight(0, 0);
	}

	/*
	 * @return total encoder counts traversed
	 */
	public double getDistanceTraveled() {
		return (leftMaster.getPosition() + rightMaster.getPosition()) / 2;
	}

	/*
	 * @return average velocity of left and right encoders (encoder counts per
	 * ms)
	 */
	public double getAverageVelocity() {
		return (leftMaster.getEncVelocity() + rightMaster.getEncVelocity()) / 2;
	}

	/*
	 * @return inches per sec
	 * 
	 * @param encoder counts per ms
	 */
	public double getInchesPerSec(double encoderRate) {
		return 1000.0 * encoderRate;
	}

	/*
	 * @return encoder counts per ms
	 * 
	 * @param inches per sec
	 */
	public double getEncoderCountsPerMs(double inchesPerSec) {
		return inchesPerSec / 1000.0;
	}

	/*
	 * @return inches
	 * 
	 * @param encoder counts
	 */
	public double getInches(double encoderCount) {
		return 0.0 * encoderCount;
	}

	/*
	 * @return encoder counts
	 * 
	 * @param inches
	 */
	public double getEncoderCounts(double inches) {
		return 0.0 * inches;
	}

	public void resetDrive() {
		leftMaster.setPosition(0);
		rightMaster.setPosition(0);
	}

	public void setHighGear() {
		shifter.set(true);
	}

	public void setLowGear() {
		shifter.set(false);
	}

	

}
