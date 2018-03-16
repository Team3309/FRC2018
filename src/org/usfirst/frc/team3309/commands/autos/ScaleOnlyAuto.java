package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraight;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class ScaleOnlyAuto extends CommandGroup {

    public ScaleOnlyAuto() {
        if (DriverStation.getInstance().getGameSpecificMessage().length() > 0) {
            if (Robot.isRightScale()) {
                addSequential(new DriveStraight(Length.fromInches(204), false));
                addSequential(new DriveArc(Length.fromInches(15), 45, 14000, false));
            } else if (Robot.isLeftScale()) {

            }

        }
    }

}
