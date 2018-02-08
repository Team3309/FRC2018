package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftSetClimbMode;

public class SetClimbMode extends CommandGroup {

    public SetClimbMode() {
        addSequential(new LiftSetClimbMode());
        addSequential(new MoveAssembly(AssemblyLocation.CLIMB_MODE));
    }

}
