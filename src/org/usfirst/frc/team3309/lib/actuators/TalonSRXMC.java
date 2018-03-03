package org.usfirst.frc.team3309.lib.actuators;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
