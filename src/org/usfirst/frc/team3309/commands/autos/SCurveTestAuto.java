package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraight;
import org.usfirst.frc.team3309.lib.math.Length;

public class SCurveTestAuto extends CommandGroup {

    @Override
    public void start() {
        addSequential(new DriveStraight(10, 25000, 0));
        addSequential(new DriveArc(Length.fromInches(7), -20, 20000, false, true)); // 16
        addSequential(new DriveArc(Length.fromInches(7), 20, 20000, false, true)); // 16
        addSequential(new DriveStraight(15, 30000, 0));
        addSequential(new DriveArc(Length.fromInches(7), 20, 20000, false, true)); // 16
        addSequential(new DriveArc(Length.fromInches(7), -20, 20000, false, true)); // 16
        addSequential(new DriveStraight(10, 17000, 0));
        super.start();
    }

}
