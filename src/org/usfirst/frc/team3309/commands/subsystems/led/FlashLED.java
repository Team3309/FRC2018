package org.usfirst.frc.team3309.commands.subsystems.led;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class FlashLED extends Command {

    private double start;
    private int numBlinks = 0;

    public FlashLED() {
        requires(Robot.led);
    }

    @Override
    protected void initialize() {
        super.initialize();
        numBlinks = 0;
        start = Timer.getFPGATimestamp();
    }

    public void execute() {
        Robot.led.set(5);
       /* double now = Timer.getFPGATimestamp();
        double timeElapsed = now - start;
        if (timeElapsed < 0.065) {
            Robot.led.set(5.0);
        } else if (timeElapsed < 0.3) {
            Robot.led.set(0);
        } else {
            start = now;
            numBlinks++;
        }*/
    }


    @Override
    protected boolean isFinished() {
   //     System.out.println("bliks " + numBlinks);
    //    return numBlinks >= 3;
        return true;
    }
}
