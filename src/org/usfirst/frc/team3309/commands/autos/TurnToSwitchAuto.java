package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTo;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTurn;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterForward;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class TurnToSwitchAuto extends CommandGroup {

    public TurnToSwitchAuto() {
        addSequential(new DriveTo(Length.fromInches(-32), true));
        if (DriverStation.getInstance().getGameSpecificMessage().length() > 0) {
            if (Robot.isLeftSwitch()) {
                addSequential(new DriveTurn(90, 0.5));
                addSequential(new DriveTo(Length.fromInches(-65), true));
                addSequential(new DriveTurn(-90, 0.5));
                addSequential(new DriveTo(Length.fromInches(-62), true));
            } else if (Robot.isRightSwitch()) {
                addSequential(new DriveTurn(-90, 0.5));
                addSequential(new DriveTo(Length.fromInches(-30), true));
                addSequential(new DriveTurn(90, 0.5));
                addSequential(new DriveTo(Length.fromInches(-50), true));

            }
            addSequential(new ShooterForward());
        }
    }

}
