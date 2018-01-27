package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.lib.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveForward extends Command {


    // inches
    private final double goalPos;
    private final double errorThreshold = 1.5;
    private double error;

    public DriveForward(Length goalPos) {
        this.goalPos = goalPos.toInches();
        requires(Robot.drive);
    }

    @Override
    protected void initialize() {
        Robot.drive.setLowGear();
        Robot.drive.enableBrakeMode(false);
        Robot.drive.setGoalPos(goalPos);
        Robot.drive.changeToPositionMode();
    }

    @Override
    protected void execute() {
        error = Robot.drive.inchesToEncoderCounts(goalPos) - Robot.drive.getEncoderPos();
        Robot.drive.setLeftRight(Robot.drive.inchesToEncoderCounts(Robot.drive.getGoalPos()),
                Robot.drive.inchesToEncoderCounts(Robot.drive.getGoalPos()));
        SmartDashboard.putNumber("drive error", error);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
