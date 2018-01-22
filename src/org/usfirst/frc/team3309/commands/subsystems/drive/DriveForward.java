package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveForward extends Command {


    // inches
    private final double goalPos;
    private final double errorThreshold = 0.5;
    private double error;

    public DriveForward(Length goalPos) {
        this.goalPos = goalPos.toInches();
        requires(Robot.drive);
    }

    @Override
    protected void initialize() {
        Robot.drive.setGoalPos(goalPos);
        Robot.drive.changeToPositionMode();
    }

    @Override
    protected void execute() {
        error = Robot.drive.inchesToEncoderCounts(goalPos) - Robot.drive.getEncoderPos();
        Robot.drive.setLeftRight(Robot.drive.getGoalPos(), Robot.drive.getGoalPos());
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(error) < Robot.drive.inchesToEncoderCounts(errorThreshold);
    }

}
