package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltBarMoveToPos;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftElevate;

public class MoveAssembly extends CommandGroup {

    public MoveAssembly(AssemblyPosition assemblyPosition) {
        addParallel(new BeltBarMoveToPos(assemblyPosition.getBeltBarPos()));
        addSequential(new LiftElevate(assemblyPosition.getElevatorPos()));
    }

}
