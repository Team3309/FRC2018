package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftSetClimbMode extends InstantCommand {

    public LiftSetClimbMode() {
        requires(Robot.lift);
    }

    @Override
    protected void execute() {
        Robot.lift.changeToPercentMode();
        Robot.lift.setLiftShifter(true);
    }

}
