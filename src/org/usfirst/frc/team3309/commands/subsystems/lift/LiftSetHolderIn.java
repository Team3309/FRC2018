package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team3309.lib.CommandEx;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftSetHolderIn extends CommandEx {

    private double startTime;

    @Override
    public void start() {
        super.start();
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    protected void execute() {
        Robot.lift.setHolderIn();
    }

    @Override
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime > 0.5;
    }

}
