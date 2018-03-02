package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltBarMoveToPos;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftElevate;
import org.usfirst.frc.team3309.lib.WaitCommand;

public class MoveAssembly extends CommandGroup {

    public MoveAssembly(AssemblyLocation assemblyLocation) {
        addSequential(new LiftElevate(assemblyLocation.getElevatorPosition()));
        addSequential(new BeltBarMoveToPos(assemblyLocation.getBeltBarPosition()));
    }

}
