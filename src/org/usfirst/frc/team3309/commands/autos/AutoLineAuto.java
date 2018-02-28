package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTo;
import org.usfirst.frc.team3309.lib.math.Length;

public class AutoLineAuto extends CommandGroup {

    public AutoLineAuto() {
        addSequential(new DriveTo(Length.fromFeet(11)));
    }

}
