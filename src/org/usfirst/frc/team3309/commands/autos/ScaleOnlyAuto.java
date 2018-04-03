package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.WaitAndMoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.MoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsClamp;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsOpen;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveEnd;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraight;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTurn;
import org.usfirst.frc.team3309.commands.subsystems.rollers.RollersActuate;
import org.usfirst.frc.team3309.commands.subsystems.rollers.RollersSetIn;
import org.usfirst.frc.team3309.lib.WaitCommand;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class ScaleOnlyAuto extends CommandGroup {

    private boolean onRight;
    private boolean shouldSwitchCube;

    private double start;

    public ScaleOnlyAuto(boolean onRight, boolean shouldSwitchCube) {
        this.onRight = onRight;
        this.shouldSwitchCube = shouldSwitchCube;
    }

    @Override
    public synchronized void start() {
        start = Timer.getFPGATimestamp();
        addParallel(new MoveAssembly(AssemblyLocation.BOTTOM));
        if (onRight) {
            if (Robot.isRightScale()) {
                addParallel(new WaitAndMoveAssembly(1.5, AssemblyLocation.SCALE_UP));
                addSequential(new DriveStraight(185, 31000, 0));
                addSequential(new DriveArc(Length.fromInches(40), -21, 26000, false, true));
                addSequential(new DriveEnd());

                addSequential(new WaitCommand(0.2));
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.4, 1.0));

                addSequential(new DriveStraight(-20, 15000, true, true));
                addSequential(new DriveEnd());
                addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));

                if (shouldSwitchCube && Robot.isRightSwitch()) {

                    addParallel(new MoveAssembly(AssemblyLocation.INTAKE));
                    addSequential(new DriveTurn(130, 1.1, true));

                    addParallel(new RollersSetIn(true));
                    addSequential(new DriveStraight(32, 15000, true, true));
                    addSequential(new WaitCommand(0.75));
                    addSequential(new ArmsClamp());
                    addSequential(new WaitCommand(0.5));
                    addSequential(new DriveStraight(-4, 22000, true, true));
                    addSequential(new RollersSetIn(false));

                    addSequential(new MoveAssembly(AssemblyLocation.SWITCH));
                    addSequential(new DriveArc(Length.fromInches(20), -13, 15000, false, true));
                    addParallel(new RollersActuate(0.4, 1.0));
                    addSequential(new ArmsOpen() {
                        @Override
                        public void end() {
                            super.end();
                            System.out.println("I ended at " + (Timer.getFPGATimestamp() - start));
                        }
                    });
                    addSequential(new WaitCommand(0.2));
                    addSequential(new DriveStraight(-20, 15000, true, true));
                    addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
                }

            } else if (Robot.isLeftScale()) {
                addSequential(new DriveStraight(133, 28000, true, 2.0));
                addSequential(new DriveArc(Length.fromInches(28), -80, 23000,false, true));
                addSequential(new DriveStraight(183, 24000, true,  2.0));
                addSequential(new DriveTurn(-120, 0.6));

                addSequential(new MoveAssembly(AssemblyLocation.SCALE_UP));
                addSequential(new WaitCommand(0.3));
                addSequential(new DriveStraight(25, 15000,  1.2));
                addSequential(new DriveEnd());
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.5, 1));

                addSequential(new DriveStraight(-25, 15000, 2.0));
                addSequential(new DriveEnd());
                addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
            }

        } else if (!onRight) {
            if (Robot.isLeftScale()) {
                addParallel(new WaitAndMoveAssembly(1.5, AssemblyLocation.SCALE_UP));
                addSequential(new DriveStraight(185, 20000, true));
                addSequential(new DriveArc(Length.fromInches(40), 24, 26000, false, true));
                addSequential(new DriveEnd());

                addSequential(new WaitCommand(0.2));
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.4, 1.0));
                addSequential(new WaitCommand(0.5));
                addSequential(new DriveStraight(-20, 15000, 1.2));
                addSequential(new DriveEnd());

                addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));

                if (Robot.isLeftSwitch() && shouldSwitchCube) {
                    addParallel(new MoveAssembly(AssemblyLocation.INTAKE));
                    addSequential(new DriveTurn(-90, 0.8));

                    addParallel(new RollersSetIn(true));
                    addSequential(new DriveStraight(29, 15000, 1.5));
                    addSequential(new WaitCommand(0.5));
                    addSequential(new ArmsClamp());
                    addSequential(new WaitCommand(0.25));
                    addSequential(new DriveStraight(-4, 20000, 1.5));
                    addSequential(new RollersSetIn(false));

                    addSequential(new MoveAssembly(AssemblyLocation.SWITCH));
                    addSequential(new DriveArc(Length.fromInches(20), 13, 15000, false, true));
                    addParallel(new RollersActuate(0.4, 1.0));
                    addSequential(new ArmsOpen());
                    addSequential(new WaitCommand(0.2));
                    addSequential(new DriveStraight(-20, 15000, 1.2));
                    addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
                }

            } else if (Robot.isRightScale()) {

                addSequential(new DriveStraight(133, 28000, true, 2.0));
                addSequential(new DriveArc(Length.fromInches(28), 80, 23000,false, true));
                addSequential(new DriveStraight(163, 24000, true,  2.0));
                addSequential(new DriveTurn(125, 0.6));

                addSequential(new MoveAssembly(AssemblyLocation.SCALE_UP));
                addSequential(new WaitCommand(0.3));
                addSequential(new DriveStraight(20, 15000,  1.2));
                addSequential(new DriveEnd());
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.5, 1));

                addSequential(new DriveStraight(-20, 15000, 1.2));
                addSequential(new DriveEnd());
                addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
            }
        }
        super.start();
    }

}
