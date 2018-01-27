package org.usfirst.frc.team3309.subsystems;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTeleop;
import org.usfirst.frc.team3309.lib.actuators.TalonSRXMC;
import org.usfirst.frc.team3309.lib.actuators.VictorSPXMC;
import org.usfirst.frc.team3309.robot.Constants;

public class Drive extends Subsystem {

    private TalonSRXMC left0 = new TalonSRXMC(Constants.DRIVE_LEFT_0_ID);
    private VictorSPXMC left1 = new VictorSPXMC(Constants.DRIVE_LEFT_1_ID);
    private VictorSPXMC left2 = new VictorSPXMC(Constants.DRIVE_LEFT_2_ID);

    private TalonSRXMC right0 = new TalonSRXMC(Constants.DRIVE_RIGHT_0_ID);
    private VictorSPXMC right1 = new VictorSPXMC(Constants.DRIVE_RIGHT_1_ID);
    private VictorSPXMC right2 = new VictorSPXMC(Constants.DRIVE_RIGHT_2_ID);

    private Solenoid shifter = new Solenoid(Constants.SHIFTER);

    private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

    private double goalPos;

    public Drive() {
        left0.changeControlMode(TalonControlMode.PercentVbus);
        left1.changeControlMode(TalonControlMode.Follower);
        left2.changeControlMode(TalonControlMode.Follower);
        left1.set(left0.getDeviceID());
        left2.set(left0.getDeviceID());
        right0.changeControlMode(TalonControlMode.PercentVbus);
        right1.changeControlMode(TalonControlMode.Follower);
        right2.changeControlMode(TalonControlMode.Follower);
        right1.set(right0.getDeviceID());
        right2.set(right0.getDeviceID());
        setHighGear();
        enableBrakeMode(false);
        left0.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
        left0.reverseSensor(true);
        right0.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveTeleop());
    }

    public double encoderCountsToInches(double counts) {
         return counts * ((Math.PI * Constants.WHEEL_DIAMETER_INCHES.toInches()) / Constants.DRIVE_ENCODER_COUNTS_PER_REV);
    }

    public double inchesToEncoderCounts(double inches) {
        return inches * Constants.DRIVE_ENCODER_COUNTS_PER_REV / (Math.PI * Constants.WHEEL_DIAMETER_INCHES.toInches());
    }

    public void resetDrive() {
        left0.setAnalogPosition(0);
        right0.setAnalogPosition(0);
        left0.setPosition(0);
        right0.setPosition(0);
        gyro.reset();
        enableBrakeMode(true);
        setLowGear();
    }

    public double getEncoderPos() {
        return (getLeftEncoder() + getRightEncoder()) / 2.0;
    }

    public double getLeftEncoder() {
        return left0.getAnalogInPosition();
    }

    public double getRightEncoder() {
        return right0.getAnalogInPosition();
    }

    public double getEncoderVelocity() {
        return (getLeftVelocity() + getRightVelocity()) / 2.0;
    }

    public double getLeftVelocity() {
        return left0.getAnalogInVelocity();
    }

    public double getRightVelocity() {
        return right0.getAnalogInVelocity();
    }

    public double getAngPos() {
        return gyro.getAngle();
    }

    public double getAngVel() {
        return gyro.getRate();
    }

    public void sendToDashboard() {
        NetworkTable table = NetworkTable.getTable("Drive");
        table.putNumber("Inches pos: ", encoderCountsToInches(getEncoderPos()));
        table.putNumber("Inches vel: ", encoderCountsToInches(getEncoderVelocity()));
        table.putNumber("Inches pos left: ", encoderCountsToInches(getLeftEncoder()));
        table.putNumber("Inches pos right: ", encoderCountsToInches(getRightEncoder()));
        table.putNumber("Enc pos: ", getEncoderPos());
        table.putNumber("Enc vel: ", getEncoderVelocity());
        table.putNumber("Left pos: ", getLeftEncoder());
        table.putNumber("Right pos: ", getRightEncoder());
        table.putNumber("Left vel: ", getLeftVelocity());
        table.putNumber("Right vel: ", getRightVelocity());
        table.putNumber("Ang pos: ", getAngPos());
        table.putNumber("Ang vel: ", getAngVel());
    }

    public void changeToPositionMode() {
        left0.changeControlMode(TalonControlMode.Position);
        right0.changeControlMode(TalonControlMode.Position);
    }

    public void changeToPercentMode() {
        left0.changeControlMode(TalonControlMode.PercentVbus);
        right0.changeControlMode(TalonControlMode.PercentVbus);
    }

    public void changeToVelocityMode() {
        left0.changeControlMode(TalonControlMode.Speed);
        right0.changeControlMode(TalonControlMode.Speed);
    }

    public void enableBrakeMode(boolean on) {
        left0.enableBrakeMode(on);
        right0.enableBrakeMode(on);
    }

    public void setVoltageRampRate(double rampRate) {
        left0.setVoltageRampRate(rampRate);
        right0.setVoltageRampRate(rampRate);
    }

    public void setLeftRight(double left, double right) {
        setLeft(-left);
        setRight(right);
    }

    public void setLeft(double power) {
        left0.set(power);
    }

    public void setRight(double power) {
      right0.set(power);
    }

    public void setHighGear() {
        shifter.set(true);
    }

    public void setLowGear() {
        shifter.set(false);
    }

    public double getGoalPos() {
        return goalPos;
    }

    public void setGoalPos(double goalPos) {
        this.goalPos = goalPos;
    }
}
