package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.robot.Robot;

public class StupidAutoTest extends Command
{
    public StupidAutoTest() {
        requires(Robot.drive);
    }

    @Override
    protected void initialize() {
        Robot.drive.changeToVelocityMode();
    }

    @Override
    protected void execute() {
        double leftVelocity = Robot.drive.inchesToEncoderCounts(7*1.25 + 4*.5);
        double rightVelocity = Robot.drive.inchesToEncoderCounts(7*1.25 - 4*.5);
        SmartDashboard.putNumber("Left velocity", leftVelocity);
        SmartDashboard.putNumber("Right Velocity", rightVelocity);
        Robot.drive.setLeftRight(leftVelocity, rightVelocity);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
