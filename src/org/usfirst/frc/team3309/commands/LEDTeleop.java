package org.usfirst.frc.team3309.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class LEDTeleop extends Command {

    public LEDTeleop() {
        requires(Robot.led);
    }


    @Override
    public void execute() {
        Robot.led.set(0);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
