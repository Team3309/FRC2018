package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.DriverStation;
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

public class CurvyToSwitchAuto extends CommandGroup {

    double start;

    @Override
    public void start() {
        start = Timer.getFPGATimestamp();
        addParallel(new MoveAssembly(AssemblyLocation.BOTTOM));
        if (DriverStation.getInstance().getGameSpecificMessage().length() > 0) {
            if (Robot.isLeftSwitch()) {
                addSequential(new DriveArc(Length.fromInches(18), -37, 31000, false, true));
                addSequential(new DriveStraight(16.0, 23000, true));
                addParallel(new MoveAssembly(AssemblyLocation.SWITCH));
                addSequential(new DriveArc(Length.fromInches(20), 20, 28000, false, true));
                addSequential(new DriveStraight(2, 28000, true));
                addSequential(new WaitCommand(0.13));
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.5, 0.2));
                addSequential(new WaitCommand(0.3));

                addParallel(new WaitAndMoveAssembly(0.5, AssemblyLocation.INTAKE));
                addSequential(new DriveArc(Length.fromInches(30), 74, 26000, true, true));
                addSequential(new DriveTurn(-70, 0.23));
                addParallel(new RollersSetIn(true));
                addSequential(new DriveStraight(20, 17000));
                addSequential(new ArmsClamp());
                addSequential(new WaitCommand(0.7));
                addParallel(new RollersSetIn(false));
                addParallel(new MoveAssembly(AssemblyLocation.EXCHANGE_ZONE));
                addSequential(new DriveStraight(-13, 17000));
                addParallel(new MoveAssembly(AssemblyLocation.SWITCH));
                addSequential(new DriveTurn(78, 0.5));
                addSequential(new DriveArc(Length.fromInches(38), 79, 27000, false, true));
                addSequential(new DriveStraight(16, 14000));
                addSequential(new ArmsOpen());
                addSequential(new WaitCommand(0.3));
                addSequential(new DriveStraight(-17, 18000));
                addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
            } else if (Robot.isRightSwitch()) {
                addSequential(new DriveArc(Length.fromInches(12), 37, 31000, false, true));
                addParallel(new MoveAssembly(AssemblyLocation.SWITCH));
                addSequential(new DriveArc(Length.fromInches(12), -22, 28000, false, true));
                addSequential(new DriveStraight(2, 28000, true));
                addSequential(new WaitCommand(0.13));
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.5, 0.2));
                addSequential(new WaitCommand(0.3));

                addParallel(new WaitAndMoveAssembly(0.5, AssemblyLocation.INTAKE));
                addSequential(new DriveArc(Length.fromInches(30), -74, 26000, true, true));
                addSequential(new DriveTurn(70, 0.23));
                addParallel(new RollersSetIn(true));
                addSequential(new DriveStraight(20, 17000));
                addSequential(new ArmsClamp());
                addSequential(new WaitCommand(0.7));
                addParallel(new RollersSetIn(false));
                addParallel(new MoveAssembly(AssemblyLocation.EXCHANGE_ZONE));
                addSequential(new DriveStraight(-13, 17000));
                addParallel(new MoveAssembly(AssemblyLocation.SWITCH));
                addSequential(new DriveTurn(-89, 0.5));
                addSequential(new DriveArc(Length.fromInches(38), -79, 27000, false, true));
                addSequential(new DriveStraight(16, 14000));
                addSequential(new ArmsOpen());
                addSequential(new WaitCommand(0.3));
                addSequential(new DriveStraight(-17, 18000));
                addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
            } else {
                DriverStation.reportError("Oh no! I don't know where to go! :karson5:", false);
            }
        }
        super.start();
    }

    @Override
    public void end() {
        super.end();
        System.out.println("I ended at " + (Timer.getFPGATimestamp() - start) + "!");
    }

}
