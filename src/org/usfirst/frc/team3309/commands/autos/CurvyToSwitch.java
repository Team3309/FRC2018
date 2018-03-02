package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DrivePath;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterShoot;
import org.usfirst.frc.team3309.robot.Constants;
import org.usfirst.frc.team3309.robot.Robot;

public class CurvyToSwitch extends CommandGroup {

    public CurvyToSwitch() {
        if (DriverStation.getInstance().getGameSpecificMessage().length() > 0) {
            if (Robot.isLeftSwitch()) {
                addSequential(new DrivePath(Constants.curvyToSwitchLeft, true));
            } else if (Robot.isRightSwitch()) {
                addSequential(new DrivePath(Constants.curvyToSwitchRight, true));
            }
            addSequential(new ShooterShoot());
        }

    }

}
