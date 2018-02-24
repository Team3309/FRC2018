package src.org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.MoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftShiftToClimbMode;

public class SetClimbMode extends CommandGroup{

    public SetClimbMode() {
        addSequential(new MoveAssembly(AssemblyLocation.CLIMB_MODE));
        addSequential(new LiftShiftToClimbMode());
    }

}
