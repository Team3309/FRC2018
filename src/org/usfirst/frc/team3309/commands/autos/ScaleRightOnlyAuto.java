package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.WaitAndMoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.MoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStop;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraightProperly;
import org.usfirst.frc.team3309.commands.subsystems.rollers.RollersActuate;
import org.usfirst.frc.team3309.lib.WaitCommand;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class ScaleRightOnlyAuto extends CommandGroup {

    @Override
    public synchronized void start() {
        addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
        if (Robot.isRightScale()) {
            addParallel(new WaitAndMoveAssembly(1.5, AssemblyLocation.SCALE_UP));
            addSequential(new DriveStraightProperly(185, 20000, true));
            addSequential(new DriveArc(Length.fromInches(40), -20, 26000, false, true));
            addSequential(new DriveStop());
            addSequential(new RollersActuate(0.5, 1.0));
            addSequential(new DriveStraightProperly(-20, 15000));
            addSequential(new DriveStop());
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
