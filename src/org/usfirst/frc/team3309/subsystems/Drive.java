package org.usfirst.frc.team3309.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
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
        left0.set(ControlMode.PercentOutput, 0);
        left1.set(ControlMode.Follower, left0.getDeviceID());
        left2.set(ControlMode.Follower, left0.getDeviceID());

        right0.set(ControlMode.PercentOutput, 0);
        right1.set(ControlMode.Follower, right0.getDeviceID());
        right2.set(ControlMode.Follower, right0.getDeviceID());

        setHighGear();
        changeToBrakeMode();

        left0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
        right0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);

        left0.setSensorPhase(false);
        right0.setSensorPhase(true);
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
        left0.getSensorCollection().setAnalogPosition(0, 0);
        right0.getSensorCollection().setAnalogPosition(0, 0);
        gyro.reset();
        changeToBrakeMode();
        setLowGear();
    }

    public double getEncoderPos() {
        return (getLeftEncoder() + getRightEncoder()) / 2.0;
    }

    public double getLeftEncoder() {
        return left0.getSensorCollection().getAnalogIn();
    }

    public double getRightEncoder() {
        return right0.getSensorCollection().getAnalogIn();
    }

    public double getEncoderVelocity() {
        return (getLeftVelocity() + getRightVelocity()) / 2.0;
    }

    public double getLeftVelocity() {
        return left0.getSensorCollection().getAnalogInVel();
    }

    public double getRightVelocity() {
        return right0.getSensorCollection().getAnalogInVel();
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

    public void changeToBrakeMode() {
        left0.setNeutralMode(NeutralMode.Brake);
        right0.setNeutralMode(NeutralMode.Brake);
    }

    public void changeToCoastMode() {
        left0.setNeutralMode(NeutralMode.Coast);
        right0.setNeutralMode(NeutralMode.Coast);
    }

    public void setHighGear() {
        shifter.set(true);
    }

    public void setLowGear() {
        shifter.set(false);
    }

    public void setLeftRight(double left, double right) {
        setLeft(left);
        setRight(right);
    }

    public void setLeft(double value) {
        left0.set(value);
    }

    public void setRight(double value) {
        right0.set(value);
    }

    public void changeToPercentMode() {
        left0.changeToPercentMode();
        right0.changeToPercentMode();
    }

    public void changeToPositionMode() {
        left0.changeToPositionMode();
        right0.changeToPositionMode();
    }

    public void changeToVelocityMode() {
        left0.changeToVelocityMode();
        right0.changeToVelocityMode();
    }

    public double getGoalPos() {
        return goalPos;
    }

    public void setGoalPos(double goalPos) {
        this.goalPos = goalPos;
    }
}
