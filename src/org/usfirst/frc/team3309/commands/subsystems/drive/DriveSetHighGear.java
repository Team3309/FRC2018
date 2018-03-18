package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveSetHighGear extends InstantCommand {

    public DriveSetHighGear() {
    }

    @Override
    protected void execute() {
        Robot.drive.setHighGear();
    }

}
