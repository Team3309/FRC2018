package org.usfirst.frc.team3309.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class FlashLED extends Command {

    public FlashLED() {
        requires(Robot.led);
    }

    public void execute() {
        Robot.led.set(1.0);
        Timer.delay(0.25);
        Robot.led.set(0.5);
        Timer.delay(0.25);
        Robot.led.set(0.5);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
