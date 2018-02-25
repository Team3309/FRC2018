package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveForward;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTurn;
import org.usfirst.frc.team3309.lib.WaitCommand;
import org.usfirst.frc.team3309.lib.math.Length;

public class TurnToSwitchAuto extends CommandGroup {

    public TurnToSwitchAuto() {
    /*    addSequential(new DriveForward(Length.fromInches(-32)));
        if (DriverStation.getInstance().getGameSpecificMessage().length() > 0) {
            if (Robot.isLeftSwitch()) {
                addSequential(new DriveTurn(90));
                addSequential(new DriveForward(Length.fromInches(-65)));
                addSequential(new DriveTurn(-90));
                addSequential(new DriveForward(Length.fromInches(-62)));
            } else {
                addSequential(new DriveTurn(-90));
            }
            addSequential(new ShooterShoot());
        }*/
        addSequential(new DriveForward(Length.fromInches(-12)));
        addSequential(new WaitCommand(1));
        addSequential(new DriveTurn(90));
        addSequential(new WaitCommand(1));
        addSequential(new DriveForward(Length.fromInches(-12)));
        addSequential(new DriveTurn(-90));
        addSequential(new WaitCommand(1));
        addSequential(new DriveForward(Length.fromInches(-12)));
    }

}
