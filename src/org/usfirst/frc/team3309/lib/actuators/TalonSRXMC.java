package org.usfirst.frc.team3309.lib.actuators;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import org.usfirst.frc.team3309.robot.Constants;

public class TalonSRXMC extends TalonSRX {

    private ControlMode controlMode;

    public TalonSRXMC(int deviceNumber) {
        super(deviceNumber);
        controlMode = ControlMode.PercentOutput;
        reset();
    }

    public void reset(){
        configOpenloopRamp(0, 0);
        configClosedloopRamp(0, 0);
        configPeakOutputForward(1, 0);
        configPeakOutputReverse(-1, 0);
        configNominalOutputForward(0, 0);
        configNominalOutputReverse(0, 0);
        configNeutralDeadband(0.04, 0);
        configVoltageCompSaturation(0, 0);
        configVoltageMeasurementFilter(32, 0);
        configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        configRemoteFeedbackFilter(getDeviceID(), RemoteSensorSource.Off, 0, 0);
        configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_100Ms, 0);
        configVelocityMeasurementWindow(64, 0);
        configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.NormallyOpen, 0);
        configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.NormallyOpen, 0);
        configForwardSoftLimitThreshold(0,0);
        configReverseSoftLimitThreshold(0, 0);
        configForwardSoftLimitEnable(false, 0);
        configReverseSoftLimitEnable(false, 0);
        config_kP(0, 0,0);
        config_kI(0, 0,0);
        config_kD(0, 0,0);
        config_kF(0, 0,0);
        config_IntegralZone(0, 0,0 );
        configAllowableClosedloopError(0, 0, 0);
        configMaxIntegralAccumulator(0, 0, 0);
        configMotionCruiseVelocity(0, 0);
        configMotionAcceleration(0,0);
        configMotionProfileTrajectoryPeriod(0, 0);
        configSetCustomParam(0, 0, 0);
        configPeakCurrentLimit(0, 0);
        configPeakCurrentDuration(0, 0);
        configContinuousCurrentLimit(0, 0);
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



}
