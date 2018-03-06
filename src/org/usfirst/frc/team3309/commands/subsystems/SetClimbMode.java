package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.usfirst.frc.team3309.commands.subsystems.falcondoors.FalconDoorsDeploy;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftManualClimb;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftManualTest;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftShiftToClimbMode;

public class SetClimbMode extends CommandGroup{

    public SetClimbMode() {
        addSequential(new LiftShiftToClimbMode());
        addSequential(new FalconDoorsDeploy());
    }

}
