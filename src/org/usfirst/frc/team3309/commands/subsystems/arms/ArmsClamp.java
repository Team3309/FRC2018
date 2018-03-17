package org.usfirst.frc.team3309.commands.subsystems.arms;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class ArmsClamp extends Command {

    private double startTime;

    public ArmsClamp() {
        requires(Robot.arms);
    }

    @Override
    public void start() {
        super.start();
        startTime = Timer.getFPGATimestamp();
    }

    protected void execute() {
       Robot.arms.clampArms();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
