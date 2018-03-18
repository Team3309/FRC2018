package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.robot.Robot;

public class DrivePower extends Command {

    public DrivePower() {
        requires(Robot.drive);
        Robot.drive.changeToPercentMode();
        SmartDashboard.putNumber("Drive power:", 0);

    }

    public void execute() {
        double power = SmartDashboard.getNumber("Drive power: ", 0);
        Robot.drive.setLeftRight(power, power);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
