package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.command.WaitForChildren;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.MoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsOpen;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltBarMoveToPos;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveEnd;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraight;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTurn;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftElevate;
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
        if (onRight) {
            if (Robot.isRightSwitch()) {
                addSequential(new LiftElevate(AssemblyLocation.SWITCH, 2.0));
                addParallel(new BeltBarMoveToPos(AssemblyLocation.SWITCH.getBeltBarPosition()));

                addSequential(new DriveStraight(97, 28000, 0));
                addSequential(new DriveEnd());
                addSequential(new DriveTurn(90, 2.3, true));
                addSequential(new DriveStraight(6, 17000, 90));

                addSequential(new WaitCommand(0.2));
                addSequential(new ArmsOpen());
                addSequential(new RollersActuate(0.4, 1.0));
                addSequential(new org.usfirst.frc.team3309.lib.WaitCommand(0.5));
                addSequential(new DriveStraight(-20, 15000, 90));
                addSequential(new DriveEnd());
                addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
            } else if (Robot.isLeftSwitch()) {
                new AutoLineAuto().start();
            }
        } else if (!onRight) {
             if (Robot.isLeftSwitch()) {
                 addSequential(new LiftElevate(AssemblyLocation.SWITCH, 2.0));
                 addParallel(new BeltBarMoveToPos(AssemblyLocation.SWITCH.getBeltBarPosition()));

                 addSequential(new DriveStraight(97, 28000, 0));
                 addSequential(new DriveTurn(-90, 1.0, true));
                 addSequential(new DriveStraight(6, 17000, -90));

                addSequential(new WaitCommand(0.2));
                addSequential(new ArmsOpen());
                addSequential(new RollersActuate(0.4, 1.0));
                addSequential(new org.usfirst.frc.team3309.lib.WaitCommand(0.5));
                addSequential(new DriveStraight(-12, 15000, -90));
                addSequential(new DriveEnd());
                addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
            } else if (Robot.isRightSwitch()) {
                 new AutoLineAuto().start();
            }
        }
        super.start();
    }

}
