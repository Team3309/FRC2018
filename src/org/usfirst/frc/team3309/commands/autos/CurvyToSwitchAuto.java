package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
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
                addSequential(new DriveStraight(10.0, 23000, true));
                addParallel(new MoveAssembly(AssemblyLocation.SWITCH));
                addSequential(new DriveArc(Length.fromInches(20), 20, 28000, false, true));
                addSequential(new DriveStraight(2, 28000, true));
                addSequential(new WaitCommand(0.13));
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.5, 0.2));
                addSequential(new WaitCommand(0.3));

                addSequential(new DriveStraight(-50, 25000, true));
                addParallel(new DriveTurn(-90, 0.3));
                addSequential(new MoveAssembly(AssemblyLocation.INTAKE));
                addParallel(new RollersSetIn(true));
                addSequential(new DriveStraight(10, 17000, true));
          //      addSequential(new DriveStraight(9, 14000, true));
                addSequential(new DriveArc(Length.fromInches(30), -90, 20000, false, true));
                addSequential(new ArmsClamp());
                addSequential(new WaitCommand(1.3));
                addSequential(new RollersSetIn(false));
                addParallel(new MoveAssembly(AssemblyLocation.EXCHANGE_ZONE));
                addSequential(new DriveStraight(-30, 22000, true));
                addSequential(new MoveAssembly(AssemblyLocation.SWITCH));
                addSequential(new DriveTurn(63, 0.4));
                addSequential(new WaitCommand(0.1));
                addSequential(new DriveStraight(47, 20000, true));
                addSequential(new DriveStraight(26, 14000, true));
                addSequential(new ArmsOpen());
                addSequential(new WaitCommand(0.3));

                addSequential(new DriveStraight(-25, 22000, true));
//                addParallel(new DriveTurn(-40, 0.3))
//                addSequential(new MoveAssembly(AssemblyLocation.INTAKE));
//                addParallel(new RollersSetIn(true));
//                addSequential(new DriveStraight(43, 17000, true));
//                addSequential(new DriveStraight(10, 14000, true));
//                addSequential(new ArmsClamp());
//                addSequential(new WaitCommand(0.7));
//                addSequential(new RollersSetIn(false));
//                addParallel(new MoveAssembly(AssemblyLocation.EXCHANGE_ZONE));
//                addSequential(new DriveStraight(-15, 22000, true));
//                addSequential(new MoveAssembly(AssemblyLocation.SWITCH));
//                addSequential(new DriveTurn(70, 0.3));
//                addSequential(new DriveStraight(10, 22000, true));
//                addSequential(new DriveStraight(6, 14000, true));
//                addSequential(new ArmsOpen());
//                addSequential(new WaitCommand(0.4));

                addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
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
