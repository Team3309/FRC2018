package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftManualForClimbing;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftSetHolderIn;
import org.usfirst.frc.team3309.lib.WaitCommand;

public class PrepareForClimb extends CommandGroup {

    public PrepareForClimb() {
        addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
        addSequential(new LiftSetHolderIn());
        addSequential(new WaitCommand(0.2));
        addSequential(new LiftManualForClimbing());
    }

}

