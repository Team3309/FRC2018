package org.usfirst.frc.team3309.commands.subsystems.led;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class FlashLED extends Command {

    private boolean isThreadActive = true;

    public FlashLED() {
        requires(Robot.led);
    }

    public void execute() {

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    try {
                        Robot.led.set(5.0);
                        sleep((long)0.065);
                        Robot.led.set(0);
                        sleep((long)0.3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                isThreadActive = false;
            }
        }.start();
    }


    @Override
    protected boolean isFinished() {
        return !isThreadActive;
    }
}
