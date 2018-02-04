package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDController;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveForward extends Command {

    // inches
    private final double goalPos;
    private final double goalAngle;
    private final double errorThreshold = 1;
    private double error;
    private PIDController turnController;

    public DriveForward(Length goalPos) {
        this(goalPos, 0);
    }

    public DriveForward(Length goalPos, double goalAngle) {
        this.goalPos = goalPos.toInches();
        this.goalAngle = goalAngle;
        requires(Robot.drive);
        turnController = new PIDController(new PIDConstants(0.1, 0, 0));
    }

    @Override
    protected void initialize() {
        Robot.drive.setLowGear();
        Robot.drive.changeToBrakeMode();
        Robot.drive.setGoalPos(goalPos);
        Robot.drive.changeToPositionMode();
    }

    @Override
    protected void execute() {
        error = Robot.drive.inchesToEncoderCounts(goalPos) - Robot.drive.getEncoderPos();
        double turn = turnController.update(Robot.drive.getAngPos(), goalAngle);
        Robot.drive.setLeftRight(Robot.drive.inchesToEncoderCounts(Robot.drive.getGoalPos()) + turn,
                Robot.drive.inchesToEncoderCounts(Robot.drive.getGoalPos()) - turn);

        SmartDashboard.putNumber("drive error", error);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.drive.encoderCountsToInches(error)) < errorThreshold;
    }

}
