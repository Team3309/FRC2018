package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.DriverStation;
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

    @Override
    public void start() {
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
                addSequential(new DriveArc(Length.fromInches(30), 70, 26000, true, true));
                addSequential(new DriveTurn(-70, 0.5));
                addParallel(new RollersSetIn(true));
                addSequential(new DriveStraight(20, 25000));
                addParallel(new MoveAssembly(AssemblyLocation.EXCHANGE_ZONE));
                addSequential(new DriveStraight(-18, 17000, true));
                addParallel(new MoveAssembly(AssemblyLocation.SWITCH));
                addSequential(new DriveTurn(-90, 0.3));
                addSequential(new DriveArc(Length.fromInches(19), 90, 20000, false, true));

            } else if (Robot.isRightSwitch()) {
                addSequential(new DriveArc(Length.fromInches(18), 37, 31000, false, true));
                addSequential(new DriveStraight(3.0, 23000, true));
                addParallel(new MoveAssembly(AssemblyLocation.SWITCH));
                addSequential(new DriveArc(Length.fromInches(20), -20, 28000, false));
                addSequential(new DriveStraight(2, 28000, true));
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.5, 1));
                addSequential(new DriveStraight(-15, 13000));
                addParallel(new MoveAssembly(AssemblyLocation.BOTTOM));
                addSequential(new DriveTurn(90, 0.5));
            /*    addParallel(new ArmsOpen());
                addSequential(new RollersSetIn(true));


                addSequential(new DriveStraight(-15, 13000));
                addParallel(new MoveAssembly(AssemblyLocation.INTAKE));
                addSequential(new DriveTurn(90, 1.0));
                addParallel(new ArmsOpen());
                addSequential(new DriveStraight(30, 20000, true));
                addSequential(new ArmsClamp());
                addSequential(new RollersSetIn(false));
                addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
                addSequential(new DriveEnd());*/
            } else {
                DriverStation.reportError("Oh no! I don't know where to go! :karson5:", false);
            }
        }
        super.start();
    }

}
