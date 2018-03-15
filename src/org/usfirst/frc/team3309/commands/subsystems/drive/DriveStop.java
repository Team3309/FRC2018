package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveStop extends Command {

    private boolean isInit = false;

    public DriveStop() {
        requires(Robot.drive);
    }

    public void initialize() {
        Robot.drive.changeToPercentMode();
        isInit = true;
    }

    public void execute(){
        if (!isInit) {
            initialize();
        }
        Robot.drive.setLeftRight(0, 0);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    public void end() {
        isInit = false;
    }

}
