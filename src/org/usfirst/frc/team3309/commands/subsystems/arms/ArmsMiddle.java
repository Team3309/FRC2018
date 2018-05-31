package org.usfirst.frc.team3309.commands.subsystems.arms;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class ArmsMiddle extends InstantCommand {

    public ArmsMiddle() {
        requires(Robot.arms);
    }

    @Override
    public void execute() {
        Robot.arms.middleArms();
    }



}
