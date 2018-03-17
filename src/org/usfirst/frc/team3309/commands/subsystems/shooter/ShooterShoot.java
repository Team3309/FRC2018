package org.usfirst.frc.team3309.commands.subsystems.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.lib.WaitCommand;

public class ShooterShoot extends CommandGroup {

    public ShooterShoot() {
        addSequential(new ShooterForward());
        addSequential(new WaitCommand(1.0));
        addSequential(new ShooterBack());
    }

}
