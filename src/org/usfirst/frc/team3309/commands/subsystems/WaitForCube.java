package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class WaitForCube extends Command {

    @Override
    protected boolean isFinished() {
        return Robot.beltBar.isCubePresent();
    }

}
