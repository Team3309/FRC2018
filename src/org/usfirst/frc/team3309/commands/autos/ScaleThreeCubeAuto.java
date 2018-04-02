package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.WaitAndMoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.MoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsOpen;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveEnd;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraight;
import org.usfirst.frc.team3309.commands.subsystems.rollers.RollersActuate;
import org.usfirst.frc.team3309.lib.WaitCommand;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class ScaleThreeCubeAuto extends CommandGroup{

    private boolean isRight;

    public ScaleThreeCubeAuto(boolean isRight){
        this.isRight = isRight;
    }

    @Override
    public void start() {
        addParallel(new MoveAssembly(AssemblyLocation.BOTTOM));
        if (isRight) {
            if (Robot.isRightScale()) {
         //       addParallel(new WaitAndMoveAssembly(1.5, AssemblyLocation.SCALE_UP));
                addSequential(new DriveStraight(20, 25000, true));
                addSequential(new DriveArc(Length.fromInches(15), 20, 26000, false, true));
           //     addSequential(new DriveStraight(5, 26000, false, true));
                addSequential(new DriveEnd());
                addSequential(new DriveArc(Length.fromInches(15), -11, 26000, false, true));
     //           addSequential(new DriveStraight(80, 25000, false, true));
        //        addSequential(new DriveArc(Length.fromInches(20), -20, 26000, false, true, true));
                addSequential(new DriveEnd());

//                addSequential(new WaitCommand(0.2));
//                addParallel(new ArmsOpen());
//                addSequential(new RollersActuate(0.4, 1.0));



            } else if (Robot.isLeftScale()) {

            }
        }

        super.start();
    }

}
