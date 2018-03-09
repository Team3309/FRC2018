package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.*;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterShoot;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class CurvyToSwitchAuto extends CommandGroup {

    @Override
    public void start() {
        if (DriverStation.getInstance().getGameSpecificMessage().length() > 0) {
            if (Robot.isLeftSwitch()) {
                addSequential(new DriveArc(Length.fromInches(33), 50, 26000, true));
                addSequential(new DriveStraightVel(-18, 18000, 10));
                addSequential(new DriveArc(Length.fromInches(33), -50, 26000, true));
                addSequential(new DriveStraightVel(-0.6, 13000, 0.5));
                addSequential(new DriveStop());
            } else if (Robot.isRightSwitch()) {
                addSequential(new DriveArc(Length.fromInches(33), -50, 26000, true));
                addSequential(new DriveStraightVel(-18, 18000, 10));
                addSequential(new DriveArc(Length.fromInches(33), 50, 26000, true));
                addSequential(new DriveStraightVel(-0.6, 13000, 0.5));
                addSequential(new DriveStop());
            } else {
                DriverStation.reportError("Oh no! I don't know where to go! :karson5:",false);
            }
            addSequential(new ShooterShoot());
        }
        super.start();
    }

}
