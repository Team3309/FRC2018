package org.usfirst.frc.team3309.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SPI;
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

    private AHRS navX = new AHRS(SPI.Port.kMXP);

    private double goalPos;

    public Drive() {
        left0.changeToPercentMode();
        left0.configPeakOutputForward(1.0, 0);
        left0.configPeakOutputReverse(-1.0, 0);
        left0.configNominalOutputForward(0.0, 0);
        left0.configNominalOutputReverse(-0.0, 0);
        left0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        left1.follow(left0);
        left2.follow(left0);
        left0.configOpenloopRamp(0.25, 0);
        left0.configMotionAcceleration(30000, 0);
        left0.config_kP(0, 0.019, 0);
        left0.config_kD(0, 0.0006, 0);
        left0.config_kF(0, 0.002, 0);

        right0.changeToPercentMode();
        right0.configPeakOutputForward(1.0, 0);
        right0.configPeakOutputReverse(-1.0, 0);
        right0.configNominalOutputForward(0.0, 0);
        right0.configNominalOutputReverse(-0.0, 0);
        right0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        right1.follow(right0);
        right2.follow(right0);
        right0.configOpenloopRamp(0.25, 0);
        right0.configMotionAcceleration(30000, 0);
        right0.config_kP(0, 0.019, 0);
        right0.config_kD(0, 0.0006, 0);
        right0.config_kF(0, 0.002, 0);

        setHighGear();
        changeToBrakeMode();
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
        left0.clearMotionProfileTrajectories();
        right0.clearMotionProfileTrajectories();
        left0.changeToDisabledMode();
        right0.changeToDisabledMode();
        left0.getSensorCollection().setQuadraturePosition(0, 0);
        right0.getSensorCollection().setQuadraturePosition(0, 0);
        navX.reset();
    }

    public double getEncoderPos() {
        return (getLeftEncoder() + getRightEncoder()) / 2.0;
    }

    public double getLeftEncoder() {
        return -left0.getSensorCollection().getQuadraturePosition();
    }

    public double getRightEncoder() {
        return right0.getSensorCollection().getQuadraturePosition();
    }

    public double getEncoderVelocity() {
        return (getLeftVelocity() + getRightVelocity()) / 2.0;
    }

    public int getLeftVelocity() {
        return left0.getSensorCollection().getQuadratureVelocity();
    }

    public double getRightVelocity() {
        return -right0.getSensorCollection().getQuadratureVelocity();
    }

    public double
    getAngPos() {
        return -navX.getAngle();
    }

    public double getAngVel() {
        return navX.getRate();
    }

    public void sendToDashboard() {
        SmartDashboard.putData(this);
        NetworkTable table = NetworkTableInstance.getDefault().getTable("Drive");
        if (left0.getControlMode() == ControlMode.Velocity || left0.getControlMode() == ControlMode.MotionMagic
                || left0.getControlMode() == ControlMode.Position) {
            table.getEntry("Target velocity left: ").setNumber(left0.getClosedLoopTarget(0));
            table.getEntry("Velocity left error: ").setNumber(left0.getClosedLoopError(0));
        }
        if (right0.getControlMode() == ControlMode.Velocity || right0.getControlMode() == ControlMode.MotionMagic
                || right0.getControlMode() == ControlMode.Position) {
            table.getEntry("Target velocity right: ").setNumber(right0.getClosedLoopTarget(0));
            table.getEntry("Velocity right error: ").setNumber(right0.getClosedLoopError(0));
        }
        table.getEntry("Position (Inches): ").setNumber(encoderCountsToInches(getEncoderPos()));
        table.getEntry("Velocity (Inches): ").setNumber(encoderCountsToInches(getEncoderVelocity()));
        table.getEntry("Left Position (Inches): ").setNumber(encoderCountsToInches(getLeftEncoder()));
        table.getEntry("Right Position (Inches): ").setNumber(encoderCountsToInches(getRightEncoder()));
        table.getEntry("Position (Raw): ").setNumber(getEncoderPos());
        table.getEntry("Velocity (Raw): ").setNumber(getEncoderVelocity());
        table.getEntry("Left Position (Raw): ").setNumber(getLeftEncoder());
        table.getEntry("Right Position (Raw): ").setNumber(getRightEncoder());
        table.getEntry("Left Velocity (Raw): ").setNumber(getLeftVelocity());
        table.getEntry("Right Velocity (Raw): ").setNumber(getRightVelocity());
        table.getEntry("Angular Position: ").setNumber(getAngPos());
        table.getEntry("Angular Velocity: ").setNumber(getAngVel());
        table.getEntry("Left output: ").setNumber(left0.getMotorOutputPercent());
        table.getEntry("Right output: ").setNumber(right0.getMotorOutputPercent());
        table.getEntry("Left control: ").setString(left0.getControlMode().toString());
        table.getEntry("Right control: ").setString(right0.getControlMode().toString());
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
        shifter.set(false);
    }

    public void setLowGear() {
        shifter.set(true);
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

    public void changeToMotionMagicMode() {
        left0.changeToMotionMagic();
        right0.changeToMotionMagic();
    }

    public void configLeftRightCruiseVelocity(double leftSpeed, double rightSpeed) {
        configLeftCruiseVelocity(leftSpeed);
        configRightCruiseVelocity(rightSpeed);
    }

    public void configRightCruiseVelocity(double sensorUnitsPer100ms) {
        right0.configMotionCruiseVelocity((int) sensorUnitsPer100ms, 0);
    }

    public void configLeftCruiseVelocity(double sensorUnitsPer100ms) {
        left0.configMotionCruiseVelocity((int) sensorUnitsPer100ms, 0);
    }

    public double getGoalPos() {
        return goalPos;
    }

    public void setGoalPos(double goalPos) {
        this.goalPos = goalPos;
    }

}
