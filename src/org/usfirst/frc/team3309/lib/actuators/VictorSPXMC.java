package org.usfirst.frc.team3309.lib.actuators;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class VictorSPXMC extends VictorSPX {

    private ControlMode controlMode = ControlMode.PercentOutput;

    public VictorSPXMC(int deviceNumber) {
        super(deviceNumber);
    }

    public void changeToPercentMode() {
        controlMode = ControlMode.PercentOutput;
    }

    public void changeToPositionMode() {
        controlMode = ControlMode.Position;
    }

    public void changeToVelocityMode() {
        controlMode = ControlMode.Velocity;
        controlMode = ControlMode.Velocity;
    }

    public void set(double value) {
        set(controlMode, value);
    }
}
