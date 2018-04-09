package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveEnd;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraight;

public class AutoLineAuto extends CommandGroup {

    public AutoLineAuto() {
        addSequential(new DriveStraight(80, DriveStraight.DriveStrategy.POSITION));
        addSequential(new DriveEnd());
    }

}

