package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltBarMoveToPos;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftElevate;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftHybridMove;

public class MoveAssembly extends CommandGroup {

    public MoveAssembly(double elevatorPos, double beltbarPos, boolean useHybrid) {
        addParallel(useHybrid ? new LiftHybridMove(elevatorPos) : new LiftElevate(elevatorPos));
        addSequential(new BeltBarMoveToPos(beltbarPos));
    }

    public MoveAssembly(double elevatorPos, double beltbarPos) {
        this(elevatorPos,beltbarPos,false);
    }

    public MoveAssembly(AssemblyLocation assemblyLocation) {
        this(assemblyLocation,false);
    }


    public MoveAssembly(AssemblyLocation assemblyLocation,boolean useHybrid)
    {
        addParallel(new BeltBarMoveToPos(assemblyLocation.getBeltBarPosition()));
        addSequential(useHybrid ? new LiftHybridMove(assemblyLocation.getElevatorPosition()) :
                new LiftElevate(assemblyLocation.getElevatorPosition()));
    }

}
