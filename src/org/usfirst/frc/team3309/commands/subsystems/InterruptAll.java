package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class InterruptAll extends InstantCommand {

    public InterruptAll() {
        requires(Robot.arms);
        requires(Robot.beltBar);
        requires(Robot.drive);
        requires(Robot.falconDoors);
        requires(Robot.lift);
        requires(Robot.rollers);
    }

}
