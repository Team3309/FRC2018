package org.usfirst.frc.team3309.commands.subsystems.arms;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class ArmsCheckForCube extends Command {

    private ArmsClamp clampArms = new ArmsClamp();

    public ArmsCheckForCube() {
    }

    @Override
    protected void execute() {
        if (Robot.arms.isCubeIn() && !clampArms.isRunning()) {
            clampArms.start();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
