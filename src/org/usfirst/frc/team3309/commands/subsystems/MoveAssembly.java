package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltBarMoveToPos;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftElevate;
import org.usfirst.frc.team3309.lib.Length;

public class MoveAssembly extends CommandGroup {

    public MoveAssembly(AssemblyLocation assemblyLocation) {
        addParallel(new BeltBarMoveToPos(assemblyLocation.getBeltBarPosition()));
        addSequential(new LiftElevate(Length.fromInches(assemblyLocation.getElevatorPosition())));
    }

}
