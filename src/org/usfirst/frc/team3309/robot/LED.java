package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LED extends Subsystem {

    private PWM indicatorLight = new PWM(Constants.LED_CHANNEL);

    public void set(int val) {
        indicatorLight.setSpeed(val);
    }

    @Override
    protected void initDefaultCommand() {
    }

}
