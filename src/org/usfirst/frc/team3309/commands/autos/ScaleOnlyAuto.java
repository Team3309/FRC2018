package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.WaitAndMoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.MoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsClamp;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsOpen;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltBarMoveToPos;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveEnd;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraight;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTurn;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftElevate;
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
                /* first cube on scale */
                addSequential(new DriveStraight(122, 40000, 0));
                addParallel(new WaitAndMoveAssembly(0.2, AssemblyLocation.SCALE_UP));
                addSequential(new DriveArc(Length.fromInches(8), -32, 25000, false, true));
                addSequential(new DriveArc(Length.fromInches(7), 24, 20000, false, true));
                addSequential(new DriveStraight(6, 12000, true, true));
                addSequential(new DriveTurn(25, 1.0, true));
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.8, 1.0));

                if (shouldSwitchCube && Robot.isRightSwitch()) {
                    /* cube on switch */
                    addParallel(new WaitAndMoveAssembly(0.2, AssemblyLocation.BOTTOM));
                    addSequential(new DriveStraight(-19, 12000, 0));
                    addSequential(new Command() {
                        @Override
                        protected boolean isFinished() {
                            return Math.abs(Robot.lift.getPosition()) < 500;
                        }
                    });
                    addParallel(new MoveAssembly(AssemblyLocation.INTAKE));
                    addSequential(new DriveTurn(152, 1.0, true));
                    addParallel(new RollersSetIn(true));
                    addSequential(new DriveStraight(13, 17000, true, true));
                    addSequential(new WaitCommand(0.5));
                    addSequential(new ArmsClamp());
                    addSequential(new WaitCommand(0.3));
                    addParallel(new MoveAssembly(AssemblyLocation.BOTTOM));
                    addSequential(new DriveStraight(-5, 17000, true, true));
                    addSequential(new RollersSetIn(false));
                    addParallel(new BeltBarMoveToPos(AssemblyLocation.SWITCH.getBeltBarPosition()));
                    addSequential(new LiftElevate(AssemblyLocation.SWITCH.getElevatorPosition(), 1.2));
                    addSequential(new Command() {
                        @Override
                        protected boolean isFinished() {
                            System.out.println("The lift ended!");
                            return true;
                        }
                    });
                    addSequential(new DriveStraight(25, 17000, true, true));
                    addParallel(new RollersActuate(0.8, 1.0));
                    addSequential(new ArmsOpen() {
                        @Override
                        public void end() {
                            super.end();
                            System.out.println("I ended at " + (Timer.getFPGATimestamp() - start));
                        }
                    });
                    addSequential(new DriveStraight(-17, 12000, true, true));
                    addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
                } else {
                    /* second cube on scale */
                    addParallel(new WaitAndMoveAssembly(0.2, AssemblyLocation.BOTTOM));
                    addSequential(new DriveStraight(-19, 12000, 0));
                    addSequential(new Command() {
                        @Override
                        protected boolean isFinished() {
                            return Math.abs(Robot.lift.getPosition()) < 500;
                        }
                    });
                    addParallel(new MoveAssembly(AssemblyLocation.INTAKE));
                    addSequential(new DriveTurn(152, 1.0, true));
                    addParallel(new RollersSetIn(true));
                    addSequential(new DriveStraight(13, 17000, true, true));
                    addSequential(new WaitCommand(0.5));
                    addSequential(new ArmsClamp());
                    addSequential(new WaitCommand(0.3));
                    addParallel(new MoveAssembly(AssemblyLocation.BOTTOM));
                    addSequential(new DriveStraight(-5, 17000, true, true));
                    addParallel(new RollersSetIn(false));
                    addSequential(new DriveTurn(0, 1.0, true));
                    addSequential(new MoveAssembly(AssemblyLocation.SCALE_UP));
                    addSequential(new DriveTurn(25, 0.8, true));
                    addSequential(new DriveStraight(20, 12000, true, true));
                    addSequential(new WaitCommand(0.5));
                    addParallel(new RollersActuate(0.8, 1.0));
                    addSequential(new ArmsOpen() {
                        @Override
                        public void end() {
                            super.end();
                            System.out.println("I ended at " + (Timer.getFPGATimestamp() - start));
                        }
                    });
                    addSequential(new DriveStraight(-17, 12000, true, true));
                    addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
                }


            } else if (Robot.isLeftScale()) {
                addSequential(new DriveStraight(120, 40000, 0));
                addSequential(new DriveArc(Length.fromInches(28), -62, 23000, false, true));
                addSequential(new DriveStraight(126, 40000, 90));
                addSequential(new DriveTurn(-5, 1.0, true));

                addSequential(new MoveAssembly(AssemblyLocation.SCALE_UP));
                addSequential(new WaitCommand(0.3));
                addSequential(new DriveStraight(17, 15000, true, true));
                addSequential(new DriveEnd());
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.5, 1));

                if (shouldSwitchCube && Robot.isLeftSwitch()) {
                    /* cube on switch */

                }  else {
                    /* second cube on scale */
                    addParallel(new WaitAndMoveAssembly(0.2, AssemblyLocation.BOTTOM));
                    addSequential(new DriveStraight(-16, 12000, 0));
                    addSequential(new Command() {
                        @Override
                        protected boolean isFinished() {
                            return Math.abs(Robot.lift.getPosition()) < 500;
                        }
                    });
                    addParallel(new MoveAssembly(AssemblyLocation.INTAKE));
                    addSequential(new DriveTurn(-165, 1.0, true));
                    addParallel(new RollersSetIn(true));
                    addSequential(new DriveStraight(13, 17000, true, true));
                    addSequential(new WaitCommand(0.5));
                    addSequential(new ArmsClamp());
                    addSequential(new WaitCommand(0.3));
                    addParallel(new MoveAssembly(AssemblyLocation.BOTTOM));
                    addSequential(new DriveStraight(-5, 17000, true, true));
                    addParallel(new RollersSetIn(false));
                    addSequential(new DriveTurn(0, 1.0, true));
                    addSequential(new MoveAssembly(AssemblyLocation.SCALE_UP));
                    addSequential(new DriveTurn(-21, 0.8, true));
                    addSequential(new DriveStraight(20, 12000, true, true));
                    addSequential(new WaitCommand(0.5));
                    addParallel(new RollersActuate(0.8, 1.0));
                    addSequential(new ArmsOpen() {
                        @Override
                        public void end() {
                            super.end();
                            System.out.println("I ended at " + (Timer.getFPGATimestamp() - start));
                        }
                    });
                    addSequential(new DriveStraight(-17, 12000, true, true));
                    addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
                }

            }

        } /*else if (!onRight) {
            if (Robot.isLeftScale()) {
                addParallel(new WaitAndMoveAssembly(1.5, AssemblyLocation.SCALE_UP));
                addSequential(new DriveStraight(185, 20000, 0));
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
                addSequential(new DriveArc(Length.fromInches(28), 80, 23000, false, true));
                addSequential(new DriveStraight(163, 24000, true, 2.0));
                addSequential(new DriveTurn(125, 0.6));

                addSequential(new MoveAssembly(AssemblyLocation.SCALE_UP));
                addSequential(new WaitCommand(0.3));
                addSequential(new DriveStraight(20, 15000, 1.2));
                addSequential(new DriveEnd());
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.5, 1));

                addSequential(new DriveStraight(-20, 15000, 1.2));
                addSequential(new DriveEnd());
                addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
            }
        }*/
        super.start();
    }

}
