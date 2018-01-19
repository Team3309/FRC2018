package org.usfirst.frc.team3309.subsystems;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import lib.actuators.TalonSRXMC;
import lib.actuators.VictorSPXMC;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTeleop;
import org.usfirst.frc.team3309.robot.RobotMap;

public class Drive extends Subsystem {

    private TalonSRXMC left0 = new TalonSRXMC(RobotMap.DRIVE_LEFT_0_ID);
    private VictorSPXMC left1 = new VictorSPXMC(RobotMap.DRIVE_LEFT_1_ID);
    private VictorSPXMC left2 = new VictorSPXMC(RobotMap.DRIVE_LEFT_2_ID);

    private TalonSRXMC right0 = new TalonSRXMC(RobotMap.DRIVE_RIGHT_0_ID);
    private VictorSPXMC right1 = new VictorSPXMC(RobotMap.DRIVE_RIGHT_1_ID);
    private VictorSPXMC right2 = new VictorSPXMC(RobotMap.DRIVE_RIGHT_2_ID);

    private Solenoid shifter = new Solenoid(RobotMap.SHIFTER);

    private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

    public Drive() {
        enableBrakeMode(false);
        left0.reverseSensor(true);
        left0.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
        right0.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
        changeToPercentMode();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveTeleop());
    }

    public double encoderCountsToInches(double counts) {
         return counts * ((Math.PI * RobotMap.WHEEL_DIAMETER_INCHES) / 4437.0);
    }

    public double inchesToEncoderCounts(double inches) {
        return inches * 4437.0 / (Math.PI * RobotMap.WHEEL_DIAMETER_INCHES);
    }

    public void resetDrive() {
        left0.reverseSensor(true);
        left0.setAnalogPosition(0);
        right0.setAnalogPosition(0);
        gyro.reset();
        enableBrakeMode(true);
        setLowGear();
    }

    public double getEncoderPos() {
        return (getLeftEncoder() + getRightEncoder()) / 2.0;
    }

    public double getLeftEncoder() {
        return left0.getPosition();
    }

    public double getRightEncoder() {
        return right0.getPosition();
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
        table.putNumber("Enc pos: ", getEncoderPos());
        table.putNumber("Enc vel: ", getEncoderVelocity());
        table.putNumber("Left pos: ", getLeftEncoder());
        table.putNumber("Right pos: ", getRightEncoder());
        table.putNumber("Left vel: ", getLeftVelocity());
        table.putNumber("Right vel: ", getRightVelocity());
        table.putNumber("Ang pos: ", getAngPos());
        table.putNumber("Ang vel: ", getAngVel());
    }

    public void changeToPercentMode() {
        left0.changeControlMode(TalonControlMode.PercentVbus);
        left1.changeControlMode(TalonControlMode.Follower);
        left2.changeControlMode(TalonControlMode.Follower);
        left0.set(left0.getDeviceID());
        left2.set(left0.getDeviceID());
        right0.changeControlMode(TalonControlMode.PercentVbus);
        right1.changeControlMode(TalonControlMode.Follower);
        right2.changeControlMode(TalonControlMode.Follower);
        right1.set(right0.getDeviceID());
        right2.set(right0.getDeviceID());
    }

    public void changeToVelocityMode() {
        left0.changeControlMode(TalonControlMode.Speed);
        right0.changeControlMode(TalonControlMode.Speed);
    }

    public void enableBrakeMode(boolean on) {
        left0.enableBrakeMode(on);
        left1.enableBrakeMode(on);
        left2.enableBrakeMode(on);
        right0.enableBrakeMode(on);
        right1.enableBrakeMode(on);
        right2.enableBrakeMode(on);
    }

    public void setVoltageRampRate(double rampRate) {
        left0.setVoltageRampRate(rampRate);
        left1.setVoltageRampRate(rampRate);
        left2.setVoltageRampRate(rampRate);
        right0.setVoltageRampRate(rampRate);
        right1.setVoltageRampRate(rampRate);
        right2.setVoltageRampRate(rampRate);
    }

    public void stopDrive() {
        setLeftRight(0, 0);
    }

    public void setLeftRight(double left, double right) {
        setLeft(-left);
        setRight(right);
    }

    public void setLeft(double power) {
        if (left0.getControlMode() == TalonControlMode.Speed) {
            left0.set(power);
        } else {
            left0.set(power);
            left1.set(power);
            left2.set(power);
        }
    }

    public void setRight(double power) {
        if (right0.getControlMode() == TalonControlMode.Speed) {
            right0.set(power);
        } else {
            right0.set(power);
            right1.set(power);
            right2.set(power);
        }
    }

    public void setHighGear() {
        shifter.set(true);
    }

    public void setLowGear() {
        shifter.set(false);
    }

}
