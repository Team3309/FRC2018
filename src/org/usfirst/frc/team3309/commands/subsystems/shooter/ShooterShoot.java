package org.usfirst.frc.team3309.commands.subsystems.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShooterShoot extends CommandGroup {

    public ShooterShoot() {
        addSequential(new ShooterForward());
        addSequential(new ShooterBack());
    }

}
