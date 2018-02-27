package org.usfirst.frc.team3309.commands.subsystems.arms;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class ArmsCheckForCube extends Command {

    private boolean isIn = false;

    public ArmsCheckForCube() {
        requires(Robot.arms);
    }

    @Override
    protected void execute() {
        if (Robot.arms.isCubeIn() && !isIn) {
            Robot.arms.clampArms();
            isIn = true;
        } else {
            isIn = false;
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
