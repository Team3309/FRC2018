package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.*;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterShoot;
import org.usfirst.frc.team3309.lib.WaitCommand;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Constants;
import org.usfirst.frc.team3309.robot.Robot;

public class CurvyToSwitchAuto extends CommandGroup {

    public CurvyToSwitchAuto() {
        if (DriverStation.getInstance().getGameSpecificMessage().length() > 0) {
            if (Robot.isLeftSwitch()) {
                addSequential(new DriveArc(Length.fromInches(33), 50, 26000, true));
                addSequential(new DriveToVel(-18, 18000, 10));
                addSequential(new DriveArc(Length.fromInches(33), -50, 26000, true));
                addSequential(new DriveToVel(-0.6, 13000, 0.5));
                addSequential(new DriveStop());
            } else if (Robot.isRightSwitch()) {
                addSequential(new DriveArc(Length.fromInches(33), -50, 26000, true));
                addSequential(new DriveToVel(-18, 18000, 10));
                addSequential(new DriveArc(Length.fromInches(33), 50, 26000, true));
                addSequential(new DriveToVel(-0.6, 13000, 0.5));
                addSequential(new DriveStop());
            }
            addSequential(new ShooterShoot());
        }
    }

}
