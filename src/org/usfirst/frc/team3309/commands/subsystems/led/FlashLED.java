package org.usfirst.frc.team3309.commands.subsystems.led;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class FlashLED extends Command {

    public FlashLED() {
        requires(Robot.led);
    }

    public void execute() {
        Robot.led.set(5.0);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
