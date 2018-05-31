package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltBarMoveToPos;
import org.usfirst.frc.team3309.robot.Robot;

public class CheckForDrop extends Command {

    @Override
    protected void execute() {
        if (!Robot.beltBar.isCubePresent()) {
            getGroup().cancel();
            new FindAndGetCube().start();
        }
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.beltBar.getGoalAngle() - Robot.beltBar.getPosition()) < BeltBarMoveToPos.ERROR_THRESHOLD;
    }
}
