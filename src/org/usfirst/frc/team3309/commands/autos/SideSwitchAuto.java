package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.MoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsOpen;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveEnd;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraight;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTurn;
import org.usfirst.frc.team3309.commands.subsystems.rollers.RollersActuate;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class SideSwitchAuto extends CommandGroup {

    private boolean onRight;

    public SideSwitchAuto(boolean onRight) {
        this.onRight = onRight;
    }

    @Override
    public void start() {
      //  addParallel(new MoveAssembly(AssemblyLocation.BOTTOM));
        if (onRight) {
            if (Robot.isRightSwitch()) {
                addSequential(new DriveStraight(83, 28000, 0));
               // addSequential(new MoveAssembly(AssemblyLocation.SWITCH));
          //      addSequential(new DriveArc(Length.fromInches(10), -58, 23000, false, true));
                addSequential(new DriveTurn(90, 1.0, true));
                addSequential(new DriveStraight(12, 20000, 90));

                addSequential(new WaitCommand(0.2));
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.4, 1.0));
                addSequential(new org.usfirst.frc.team3309.lib.WaitCommand(0.5));
                addSequential(new DriveStraight(-20, 15000, 90));
                addSequential(new DriveEnd());
             //   addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
            } else if (Robot.isLeftSwitch()) {
                new AutoLineAuto().start();
            }
        } else if (!onRight) {
             if (Robot.isLeftSwitch()) {
                addSequential(new DriveStraight(83, 28000, 0));
           //     addSequential(new MoveAssembly(AssemblyLocation.SWITCH));
                 addSequential(new DriveTurn(-90, 1.0, true));
                 addSequential(new DriveStraight(12, 20000, -90));

                addSequential(new WaitCommand(0.2));
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.4, 1.0));
                addSequential(new org.usfirst.frc.team3309.lib.WaitCommand(0.5));
                addSequential(new DriveStraight(-20, 15000, -90));
                addSequential(new DriveEnd());
             //   addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
            } else if (Robot.isRightSwitch()) {
                 new AutoLineAuto().start();
            }
        }
        super.start();
    }

}
