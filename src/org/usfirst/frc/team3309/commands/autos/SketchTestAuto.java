package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveEnd;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTurn;
import org.usfirst.frc.team3309.lib.math.Length;

public class SketchTestAuto extends CommandGroup {

    @Override
    public synchronized void start() {
        addSequential(new DriveArc(Length.fromInches(20), 15, 12000, true));
        addSequential(new DriveEnd());
        addSequential(new DriveTurn(90, 5.0, true));
        super.start();
    }
}
