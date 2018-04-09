package org.usfirst.frc.team3309.commands.subsystems.arms;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class ArmsClamp extends InstantCommand {

    public ArmsClamp() {
        requires(Robot.arms);
    }

    @Override
    public void start() {
        super.start();
    }

    protected void execute() {
       Robot.arms.clampArms();
    }

}
