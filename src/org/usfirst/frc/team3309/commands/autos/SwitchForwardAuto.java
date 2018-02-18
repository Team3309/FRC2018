package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveForward;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterShoot;
import org.usfirst.frc.team3309.lib.math.Length;

public class SwitchForwardAuto extends CommandGroup {

    public SwitchForwardAuto() {
        addSequential(new DriveForward(Length.fromInches(50)));
        addSequential(new ShooterShoot());
    }

}
