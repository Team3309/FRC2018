package org.usfirst.frc.team3309.lib.actuators;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TalonSRXMC extends TalonSRX {

    private ControlMode controlMode;

    public TalonSRXMC(int deviceNumber) {
        super(deviceNumber);
        controlMode = ControlMode.PercentOutput;
        reset();
    }

    public void reset(){
        configOpenloopRamp(0, 10);
        configClosedloopRamp(0, 10);
        configPeakOutputForward(1, 10);
        configPeakOutputReverse(-1, 10);
        configNominalOutputForward(0, 10);
        configNominalOutputReverse(0, 10);
        configNeutralDeadband(0.04, 10);
        configVoltageCompSaturation(0, 10);
        configVoltageMeasurementFilter(32, 10);
        configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        configRemoteFeedbackFilter(getDeviceID(), RemoteSensorSource.Off, 0,10);
        configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_100Ms, 10);
        configVelocityMeasurementWindow(64, 10);
        configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.NormallyOpen, 10);
        configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.NormallyOpen, 10);
        configForwardSoftLimitThreshold(0,10);
        configReverseSoftLimitThreshold(0, 10);
        configForwardSoftLimitEnable(false, 10);
        configReverseSoftLimitEnable(false, 10);
        config_kP(0, 0,10);
        config_kI(0, 0,10);
        config_kD(0, 0,10);
        config_kF(0, 0,10);
        config_IntegralZone(0, 0,10 );
        configAllowableClosedloopError(0, 0, 10);
        configMaxIntegralAccumulator(0, 0, 10);
        configMotionCruiseVelocity(0, 10);
        configMotionAcceleration(0,10);
        configMotionProfileTrajectoryPeriod(0, 10);
        configSetCustomParam(0, 0, 10);
        configPeakCurrentLimit(0, 10);
        configPeakCurrentDuration(0, 10);
        configContinuousCurrentLimit(0, 10);
    }

    public void changeToPercentMode() {
        controlMode = ControlMode.PercentOutput;
    }

    public void changeToPositionMode() {
       controlMode = ControlMode.Position;
    }

    public void changeToVelocityMode() {
        controlMode = ControlMode.Velocity;
    }

    public void changeToMotionMagic() { controlMode = ControlMode.MotionMagic; }

    public void changeToDisabledMode() { set(ControlMode.Disabled, 0); }

    public void set(double value) {
        /*
        * Apparent talon bug where goal velocity is exactly half
        * */
        if (controlMode == ControlMode.Velocity) {
            value *= 2;
        }
        set(controlMode, value);
    }

    public TalonSRX getTalonSRX() {
        return this;
    }



}
