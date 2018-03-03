package org.usfirst.frc.team3309.commands.subsystems.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.lib.WaitCommand;

public class ShooterShoot extends CommandGroup {

    public ShooterShoot() {
        addSequential(new ShooterForward());
        addSequential(new WaitCommand(0.5));
        addSequential(new ShooterBack());
    }

}
