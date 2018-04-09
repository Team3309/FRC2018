package org.usfirst.frc.team3309.commands.subsystems.falcondoors;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class FalconDoorsDeploy extends InstantCommand {

    public FalconDoorsDeploy() {
        requires(Robot.falconDoors);
    }

    @Override
    protected void execute() {
        Robot.falconDoors.setDown();
    }

}
