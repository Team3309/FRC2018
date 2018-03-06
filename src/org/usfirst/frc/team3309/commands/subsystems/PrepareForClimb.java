package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftManualClimb;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftSetHolderIn;

public class PrepareForClimb extends CommandGroup {

    public PrepareForClimb() {
        addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
        addSequential(new LiftSetHolderIn());
        addSequential(new MoveAssembly(AssemblyLocation.CLIMB));
        addSequential(new LiftManualClimb());
    }

}
