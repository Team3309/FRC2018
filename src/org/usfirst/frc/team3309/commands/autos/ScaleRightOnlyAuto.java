package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.MoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStop;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraightProperly;
import org.usfirst.frc.team3309.commands.subsystems.rollers.RollersActuate;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class ScaleRightOnlyAuto extends CommandGroup {

    @Override
    public synchronized void start() {
        addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
        if (Robot.isRightScale()) {
            addSequential(new DriveStraightProperly(185, 20000, true));
         //   addParallel(new MoveAssembly(AssemblyLocation.SCALE_MIDDLE));
            addSequential(new DriveArc(Length.fromInches(40), -34, 26000, false, true));
            addSequential(new DriveStop());
         //   addSequential(new RollersActuate(0.5, 2.0));
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
