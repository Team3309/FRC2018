package org.usfirst.frc.team3309.commands.subsystems.led;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class LEDOff extends Command {

    public LEDOff() {
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
