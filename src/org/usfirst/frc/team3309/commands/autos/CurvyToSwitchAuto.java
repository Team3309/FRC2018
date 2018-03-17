package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStop;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraightProperly;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraightVel;
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
                addSequential(new DriveStraightProperly(-4, 23000));
                addSequential(new DriveArc(Length.fromInches(26), -40, 24000, true));
                addSequential(new DriveStraightProperly(-0.4, 8000));
                addSequential(new DriveStop());
            } else if (Robot.isRightSwitch()) {
                addSequential(new DriveArc(Length.fromInches(14), -40, 31000, true));
                addSequential(new DriveStraightProperly(-2.0, 23000));
                addSequential(new DriveArc(Length.fromInches(14), 34, 28000, true));
                addSequential(new DriveStraightProperly(-2, 18000));
                addSequential(new DriveStop());
            } else {
                DriverStation.reportError("Oh no! I don't know where to go! :karson5:",false);
            }
            addSequential(new WaitCommand(0.30));
            addSequential(new ShooterShoot());
        }
        super.start();
    }

}
