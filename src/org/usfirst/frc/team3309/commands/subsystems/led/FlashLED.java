package org.usfirst.frc.team3309.commands.subsystems.led;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class FlashLED extends Command {

    public FlashLED() {
        requires(Robot.led);
    }

    public void execute() {
        blink();
    }

    private void blink() {
        Robot.led.set(5.0);
        Timer.delay(0.065);
        Robot.led.set(0);
        Timer.delay(0.25);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
