package org.usfirst.frc.team3309.subsystems;

import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.commands.subsystems.led.LEDOff;
import org.usfirst.frc.team3309.robot.Constants;

public class LED extends Subsystem {

    private AnalogOutput indicatorLight = new AnalogOutput(Constants.LED_CHANNEL);

    public void set(double val) {
        indicatorLight.setVoltage(val);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new LEDOff());
    }

}
