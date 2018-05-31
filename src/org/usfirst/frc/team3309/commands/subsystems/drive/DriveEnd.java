package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveEnd extends Command {

    public DriveEnd() {
        requires(Robot.drive);
    }


    public void execute(){
        Robot.drive.disableOutput();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}
