package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.controllers.drive.DrivePositionController;
import org.usfirst.frc.team3309.lib.controllers.drive.DriveSignal;
import org.usfirst.frc.team3309.lib.controllers.drive.DriveState;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.robot.Robot;
import org.usfirst.frc.team3309.robot.RobotMap;

public class DriveForward extends Command {


    // inches
    private final double goalPos;
    private final double errorThreshold = 0.5;
    private double error;

    public DriveForward(double goalPos) {
        this.goalPos = goalPos;
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
