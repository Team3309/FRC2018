package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsClamp;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsOpen;
import org.usfirst.frc.team3309.commands.subsystems.rollers.RollersActuate;
import org.usfirst.frc.team3309.lib.WaitCommand;
import org.usfirst.frc.team3309.robot.Constants;

public class FindAndGetCube extends CommandGroup {

    public FindAndGetCube() {
        addParallel(new ArmsOpen());
        addParallel(new RollersActuate(Constants.AUTO_ROLLER_INTAKE_POWER,3));
        addSequential(new MoveAssembly(AssemblyLocation.BOTTOM_FOR_CUBE));
        addSequential(new WaitForCube());
        addSequential(new ArmsClamp());
        addSequential(new WaitCommand(0.6));
        addParallel(new CheckForDrop());
        addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
    }
}
