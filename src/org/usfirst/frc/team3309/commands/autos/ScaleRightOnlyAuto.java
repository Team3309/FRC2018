package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.MoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStop;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraightProperly;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class ScaleRightOnlyAuto extends CommandGroup {

    @Override
    public synchronized void start() {
        addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
        if (Robot.isRightScale()) {
            addSequential(new DriveStraightProperly(155, 20000, true));
            addSequential(new DriveArc(Length.fromInches(60), -25, 26000, false, true));
            addSequential(new DriveStop());
        //    addSequential(new DriveStraightProperly(20, 10000));
        } else if (Robot.isLeftScale()) {
 /*           addSequential(new DriveStraightProperly(115, 30000));
            addSequential(new DriveArc(Length.fromInches(30), 90, 26000, false));
            addSequential(new DriveStraightProperly(144, 30000));
            addSequential(new DriveArc(Length.fromInches(30), -90, 26000, false));
            addSequential(new DriveStraightProperly(33, 30000));*/
        }
        super.start();
    }

}
