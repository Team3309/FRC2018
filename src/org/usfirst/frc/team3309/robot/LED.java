package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LED extends Subsystem {

    private PWM indicatorLight = new PWM(Constants.LED_CHANNEL);

    public void set(int val) {
        indicatorLight.setRaw(val);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new Command() {
            @Override
            public void initialize() {
                requires(Robot.led);
            }


            @Override
            public void execute() {
                indicatorLight.setRaw(0);
            }

            @Override
            protected boolean isFinished() {
                return false;
            }
        });
    }

}
