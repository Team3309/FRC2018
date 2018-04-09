package org.usfirst.frc.team3309.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.MoveAssembly;

public class WaitAndMoveAssembly extends CommandGroup {

    public WaitAndMoveAssembly(double wait, AssemblyLocation assemblyLocation) {
        addSequential(new WaitCommand(wait));
        addSequential(new MoveAssembly(assemblyLocation));
    }

    public WaitAndMoveAssembly(double wait, double elevatorPos, double beltbarPos) {
        addSequential(new WaitCommand(wait));
        addSequential(new MoveAssembly(elevatorPos, beltbarPos));
    }

}
