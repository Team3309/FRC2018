package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.lib.WaitCommand;

public class IntakeCube extends CommandGroup {

    public IntakeCube() {
        addSequential(new MoveAssembly(AssemblyLocation.BOTTOM_FOR_CUBE));
//        addSequential(new CheckForCube());
//        addSequential(new WaitCommand(0.6));
//        addParallel(new CheckForDrop());
//        addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
//        Andrew said to 🐈 on auto-pickup.
    }

}
