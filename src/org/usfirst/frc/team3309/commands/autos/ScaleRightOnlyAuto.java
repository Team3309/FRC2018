package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.WaitAndMoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.MoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsOpen;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStop;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraightProperly;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTurn;
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
            addParallel(new ArmsOpen());
            addSequential(new RollersActuate(0.5, 1.0));
            addSequential(new WaitCommand(0.5));
            addSequential(new DriveStraightProperly(-20, 15000, 2.0));
            addSequential(new DriveStop());
            addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
        } else if (Robot.isLeftScale()) {
            addSequential(new DriveStraightProperly(133, 28000, true, 2.0));
            addSequential(new DriveArc(Length.fromInches(28), -80, 23000,false, true));
            addSequential(new DriveStraightProperly(135, 28000,  1.8));
            addSequential(new DriveTurn(-125, 0.6));
            addSequential(new DriveStraightProperly(40, 15000, 0.7));
        //    addSequential(new DriveArc(Length.fromInches(8), 110, 18000, false, true));
            addSequential(new DriveStop());
        }
        super.start();
    }

}
