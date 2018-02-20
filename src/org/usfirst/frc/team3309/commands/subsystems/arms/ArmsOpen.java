package org.usfirst.frc.team3309.commands.subsystems.arms;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class ArmsOpen extends InstantCommand {

    public ArmsOpen() {
        requires(Robot.arms);
    }

    protected void execute() {
        Robot.arms.openArms();
    }

}
