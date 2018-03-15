package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltBarMoveToPos;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftElevate;
import org.usfirst.frc.team3309.lib.WaitCommand;

public class MoveAssembly extends CommandGroup {

    public MoveAssembly(double elevatorPos, double beltbarPos) {
        addSequential(new LiftElevate(elevatorPos));
        addSequential(new BeltBarMoveToPos(beltbarPos));
    }

    public MoveAssembly(AssemblyLocation assemblyLocation) {
        addParallel(new BeltBarMoveToPos(assemblyLocation.getBeltBarPosition()));
        addSequential(new LiftElevate(assemblyLocation.getElevatorPosition()));
    }

}
