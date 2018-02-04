package org.usfirst.frc.team3309.lib.actuators;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TalonSRXMC extends TalonSRX {

    private ControlMode controlMode;

    public TalonSRXMC(int deviceNumber) {
        super(deviceNumber);
        controlMode = ControlMode.PercentOutput;
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

    public void set(double value) {
        set(controlMode, value);
    }

}
