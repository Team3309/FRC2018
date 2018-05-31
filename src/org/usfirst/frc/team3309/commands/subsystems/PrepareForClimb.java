package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftManualForClimbing;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftSetHolderIn;
import org.usfirst.frc.team3309.lib.WaitCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class PrepareForClimb extends CommandGroup {

    public PrepareForClimb() {
       // addSequential(new MoveAssembly(AssemblyLocation.CLIMB));
        addSequential(new LiftSetHolderIn());
        addSequential(new WaitCommand(0.2));
        addSequential(new Command() {

            @Override
            public void execute() {
                Robot.beltBar.setClimbing(true);
            }

            @Override
            protected boolean isFinished() {
                return true;
            }
        });
        addSequential(new LiftManualForClimbing());
    }

}

