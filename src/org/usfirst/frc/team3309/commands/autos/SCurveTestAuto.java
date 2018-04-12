package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.lib.math.Length;

public class SCurveTestAuto extends CommandGroup {

    @Override
    public void start() {
        addSequential(new DriveArc(Length.fromInches(13), -37, 27000, false, true)); // 16
        addSequential(new DriveArc(Length.fromInches(13), 37, 27000, false, true)); // 16
        super.start();
    }

}
