package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveForward;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTurn;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterForward;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterShoot;
import org.usfirst.frc.team3309.lib.WaitCommand;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class TurnToSwitchAuto extends CommandGroup {

    public TurnToSwitchAuto() {
        addSequential(new DriveForward(Length.fromInches(-32)));
        if (DriverStation.getInstance().getGameSpecificMessage().length() > 0) {
            if (Robot.isLeftSwitch()) {
                addSequential(new DriveTurn(90, 0.5));
                addSequential(new DriveForward(Length.fromInches(-65)));
                addSequential(new DriveTurn(-90, 0.5));
                addSequential(new DriveForward(Length.fromInches(-62)));
            } else if (Robot.isRightSwitch()) {
                addSequential(new DriveTurn(-90, 0.5));
                addSequential(new DriveForward(Length.fromInches(-30)));
                addSequential(new DriveTurn(90, 0.5));
                addSequential(new DriveForward(Length.fromInches(-50)));

            }
            addSequential(new ShooterForward());
        }
    }

}
