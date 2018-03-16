package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStop;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraight;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraightVel;
import org.usfirst.frc.team3309.lib.WaitCommand;
import org.usfirst.frc.team3309.lib.math.Length;

public class AutoLineAuto extends CommandGroup {

    public AutoLineAuto() {
        addSequential(new DriveStraightVel(20, 20000, 5));
        addSequential(new DriveStraightVel(5, 10000, 3));
        addSequential(new DriveStop());
    }

}

