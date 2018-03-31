package org.usfirst.frc.team3309.commands.subsystems.arms;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class ArmsMiddle extends Command {

    public ArmsMiddle() {
        requires(Robot.arms);
    }

    @Override
    public void start() {
        super.start();
    }

    protected void execute() {
        Robot.arms.middleArms();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}
