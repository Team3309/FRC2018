package org.usfirst.frc.team3309.commands.subsystems.falcondoors;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class FalconDoorsRetract extends InstantCommand {

    public FalconDoorsRetract() {
        requires(Robot.falconDoors);
    }

    @Override
    public void execute() {
        Robot.falconDoors.setUp();
    }

}
