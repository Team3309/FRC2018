package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.MoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsOpen;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveEnd;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraight;
import org.usfirst.frc.team3309.commands.subsystems.rollers.RollersActuate;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterShoot;
import org.usfirst.frc.team3309.lib.WaitCommand;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class CurvyToSwitchAuto extends CommandGroup {

    @Override
    public void start() {
        if (DriverStation.getInstance().getGameSpecificMessage().length() > 0) {
            if (Robot.isLeftSwitch()) {
                addSequential(new DriveArc(Length.fromInches(26), 50, 28000, true));
                addSequential(new DriveStraight(-8, 23000, true));
                addSequential(new DriveArc(Length.fromInches(10), -35, 24000, true));
                addSequential(new DriveStraight(-7, 28000));
                addSequential(new DriveEnd());
            } else if (Robot.isRightSwitch()) {
                addSequential(new DriveArc(Length.fromInches(18), 40, 31000, false, true));
                addSequential(new DriveStraight(4.0, 23000, true));
                addParallel(new MoveAssembly(AssemblyLocation.SWITCH));
                addSequential(new DriveArc(Length.fromInches(20), -30, 28000, false, true));
                addSequential(new DriveStraight(7, 28000));
                addParallel(new ArmsOpen());
                addSequential(new RollersActuate(0.5, 1));
                addSequential(new DriveStraight(-15, 13000));
                addSequential(new MoveAssembly(AssemblyLocation.BOTTOM));
                addSequential(new DriveEnd());
            } else {
                DriverStation.reportError("Oh no! I don't know where to go! :karson5:",false);
            }
        }
        super.start();
    }

}
