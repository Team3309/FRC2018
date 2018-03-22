package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveEnd;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraight;
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
                addSequential(new DriveStraight(-3, 10000));
                addSequential(new DriveEnd());
            } else if (Robot.isRightSwitch()) {
                addSequential(new DriveArc(Length.fromInches(18), -40, 31000, true));
                addSequential(new DriveStraight(-8.0, 23000));
                addSequential(new DriveArc(Length.fromInches(20), 30, 28000, true));
                addSequential(new DriveStraight(-2, 10000));
                addSequential(new DriveEnd());
            } else {
                DriverStation.reportError("Oh no! I don't know where to go! :karson5:",false);
            }
            addSequential(new WaitCommand(0.30));
            addSequential(new ShooterShoot());
        }
        super.start();
    }

}
