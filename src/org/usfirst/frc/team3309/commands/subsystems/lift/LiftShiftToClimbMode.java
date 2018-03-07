package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftShiftToClimbMode extends InstantCommand {

    public LiftShiftToClimbMode() {
    }

    @Override
    protected void execute() {
        Robot.lift.setLiftShifter(false);
    }

}
