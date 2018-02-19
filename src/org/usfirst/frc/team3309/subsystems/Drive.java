package org.usfirst.frc.team3309.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    private AHRS navX = new AHRS(SerialPort.Port.kMXP);

    private double goalPos;

    public Drive() {
        left0.set(ControlMode.PercentOutput, 0);
        left1.follow(left0);
        left2.follow(left0);
        left0.configOpenloopRamp(0.25, 0);

        right0.set(ControlMode.PercentOutput, 0);
        right1.follow(right0);
        right2.follow(right0);
        right0.configOpenloopRamp(0.25, 0);

        setHighGear();
        changeToBrakeMode();

        left0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        right0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

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

    public void reset() {
        left0.getSensorCollection().setQuadraturePosition(0, 0);
        right0.getSensorCollection().setQuadraturePosition(0, 0);
        navX.reset();
        changeToBrakeMode();
        setLowGear();
    }

    public double getEncoderPos() {
        return (getLeftEncoder() + getRightEncoder()) / 2.0;
    }

    public double getLeftEncoder() {
        return left0.getSensorCollection().getQuadraturePosition();
    }

    public double getRightEncoder() {
        return right0.getSensorCollection().getQuadraturePosition();
    }

    public double getEncoderVelocity() {
        return (getLeftVelocity() + getRightVelocity()) / 2.0;
    }

    public double getLeftVelocity() {
        return left0.getSensorCollection().getQuadratureVelocity();
    }

    public double getRightVelocity() {
        return right0.getSensorCollection().getQuadratureVelocity();
    }

    public double getAngPos() {
        return navX.getAngle();
    }

    public double getAngVel() {
        return navX.getRate();
    }

    public void sendToDashboard() {
        SmartDashboard.putData(this);
        NetworkTable table = NetworkTableInstance.getDefault().getTable("Drive");
        table.getEntry("Position (Inches): ").setNumber(encoderCountsToInches(getEncoderPos()));
        table.getEntry("Velocity (Inches): ").setNumber(encoderCountsToInches(getEncoderVelocity()));
        table.getEntry("Left Position (Inches): ").setNumber(encoderCountsToInches(getLeftEncoder()));
        table.getEntry("Right Position (Inches): ").setNumber(encoderCountsToInches(getRightEncoder()));
        table.getEntry("Position (Raw): ").setNumber(getEncoderPos());
        table.getEntry("Velocity (Raw): ").setNumber(getEncoderVelocity());
        table.getEntry("Left Position (Raw): ").setNumber(encoderCountsToInches(getLeftEncoder()));
        table.getEntry("Right Position (Raw): ").setNumber(encoderCountsToInches(getRightEncoder()));
        table.getEntry("Left Velocity (Raw): ").setNumber(getLeftVelocity());
        table.getEntry("Angular Position: ").setNumber(getAngPos());
        table.getEntry("Angular Velocity: ").setNumber(getAngVel());
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
        right0.set(-value);
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

    public double getGoalPos() { return goalPos; }

    public void setGoalPos(double goalPos) {
        this.goalPos = goalPos;
    }
}
