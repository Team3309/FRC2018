package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitForChildren;
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

        boolean isRightScale = Robot.isRightScale();
        boolean isRightSwitch = Robot.isRightSwitch();
        boolean isLeftScale = Robot.isLeftScale();
        boolean isLeftSwitch = Robot.isRightScale();

        boolean shouldScale = true;

        if (onRight) {
            if (isRightScale) {
                /* first cube on scale */
                addSequential(new DriveStraight(126, 40000, 0)); // 122
                addParallel(new WaitAndMoveAssembly(0.2, AssemblyLocation.SCALE_UP));
                addSequential(new DriveArc(Length.fromInches(5), -22, 25000, false, true)); // 8, -32
                addSequential(new DriveEnd());
                addSequential(new DriveArc(Length.fromInches(4), 22, 20000, false, true)); // 7
                addSequential(new DriveEnd());
                addSequential(new DriveStraight(6, 12000, true, true));
                addSequential(new DriveEnd());
                addSequential(new DriveTurn(25, 1.0, true));
                addSequential(new DriveEnd());
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.8, 1.0));

                if (shouldSwitchCube && isRightSwitch) {
                    /* cube on switch */
                    addParallel(new WaitAndMoveAssembly(0.2, AssemblyLocation.BOTTOM));
                    addSequential(new DriveStraight(-8, 12000, 0));
                    addSequential(new Command() {
                        @Override
                        protected boolean isFinished() {
                            return Math.abs(Robot.lift.getPosition()) < 500;
                        }
                    });
                    addParallel(new MoveAssembly(AssemblyLocation.INTAKE));
                    addSequential(new DriveTurn(190, 100.0, true)); // 152
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
                } else if (shouldScale) {
                    /* second cube on scale */
                    addParallel(new WaitAndMoveAssembly(0.2, AssemblyLocation.BOTTOM));
                    addSequential(new DriveStraight(-16, 12000, 0));
                    addSequential(new WaitForChildren());

                    addSequential(new DriveEnd());
                    addParallel(new MoveAssembly(AssemblyLocation.INTAKE));
                    addSequential(new DriveTurn(163, 1.0, true)); // 152
                    addParallel(new RollersSetIn(true));
                    addSequential(new DriveStraight(13, 17000, true, true));
                    addSequential(new WaitCommand(0.5));
                    addSequential(new ArmsClamp());
                    addSequential(new WaitCommand(0.3));
                    addParallel(new MoveAssembly(AssemblyLocation.BOTTOM));
                    addSequential(new DriveStraight(-5, 17000, true, true));
                    addSequential(new DriveEnd());
                    addParallel(new RollersSetIn(false));
                    addSequential(new DriveTurn(6, 1.0, true));
                    addSequential(new MoveAssembly(AssemblyLocation.SCALE_UP));
                    addSequential(new DriveStraight(20, 12000, true, true));
                    addSequential(new WaitCommand(0.5));
                    addParallel(new RollersActuate(0.6, 1.0));
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
            } else if (isLeftScale) {
                addSequential(new DriveStraight(134, 40000, 0));
                addSequential(new DriveArc(Length.fromInches(28), -70, 23000, false, true));

                addSequential(new DriveStraight(93, 40000, 90)); // 126
                addSequential(new DriveEnd());
                addSequential(new DriveTurn(-15, 2.0, true));
                addSequential(new DriveEnd());
                addSequential(new MoveAssembly(AssemblyLocation.SCALE_UP));
                addSequential(new WaitCommand(0.2));
                addSequential(new DriveStraight(19, 15000, true, true));
                addSequential(new DriveEnd());
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.5, 1));

                /* get second cube */
                addSequential(new DriveEnd());
                addParallel(new WaitAndMoveAssembly(0.2, AssemblyLocation.BOTTOM));
                addSequential(new DriveStraight(-16, 12000, 0));
                addSequential(new Command() {
                    @Override
                    protected boolean isFinished() {
                        return Math.abs(Robot.lift.getPosition()) < 500;
                    }
                });
                addSequential(new DriveEnd());
                addSequential(new MoveAssembly(AssemblyLocation.INTAKE));
                addSequential(new DriveEnd());
                addSequential(new DriveTurn(-159, 1.0, true));
                addSequential(new RollersSetIn(true));
                addSequential(new DriveStraight(18, 17000, true, true));
                addSequential(new DriveEnd());

                if (shouldSwitchCube && isLeftSwitch) {
                    /* cube on switch */
                    addSequential(new WaitCommand(0.35));
                    addSequential(new ArmsClamp());
                    addSequential(new WaitCommand(0.15));
                    addParallel(new MoveAssembly(AssemblyLocation.BOTTOM));
                    addSequential(new DriveStraight(-5, 17000, true, true));
                    addParallel(new RollersSetIn(false));
                    addParallel(new BeltBarMoveToPos(AssemblyLocation.SWITCH.getBeltBarPosition()));
                    addSequential(new LiftElevate(AssemblyLocation.SWITCH.getElevatorPosition(), 0.9));
                    addSequential(new Command() {
                        @Override
                        protected boolean isFinished() {
                            System.out.println("The lift ended!");
                            return true;
                        }
                    });
                    addSequential(new DriveStraight(28, 17000, true, true));
                    addParallel(new RollersActuate(0.8, 1.0));
                    addSequential(new ArmsOpen() {
                        @Override
                        public void end() {
                            super.end();
                            System.out.println("I ended at " + (Timer.getFPGATimestamp() - start));
                        }
                    });
                    addSequential(new DriveStraight(-10, 12000, true, true));
                    addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
                } else if (shouldScale) {
                    /* second cube on scale */
                    addSequential(new WaitCommand(0.5));
                    addSequential(new ArmsClamp());
                    addSequential(new WaitCommand(0.3));
                    addParallel(new MoveAssembly(AssemblyLocation.BOTTOM));
                    addSequential(new DriveStraight(-5, 17000, true, true));
                    addSequential(new DriveEnd());

                    addSequential(new RollersSetIn(false));
                    addSequential(new DriveTurn(0, 1.0, true));

                    addSequential(new MoveAssembly(AssemblyLocation.SCALE_UP));
                    addSequential(new DriveStraight(20, 12000, true, true));
                    addSequential(new DriveEnd());
                    addSequential(new DriveTurn(-21, 0.8, true));
                    addParallel(new RollersActuate(0.6, 1.0));
                    addSequential(new ArmsOpen() {
                        @Override
                        public void end() {
                            super.end();
                            System.out.println("I ended at " + (Timer.getFPGATimestamp() - start));
                        }
                    });
                    addSequential(new DriveEnd());
                    addParallel(new DriveStraight(-10, 12000, true, true));
                    addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
                }
            }
        }
        super.start();
    }

}
