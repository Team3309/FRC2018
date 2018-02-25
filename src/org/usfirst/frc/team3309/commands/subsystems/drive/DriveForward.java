package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.LibTimer;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDController;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveForward extends Command {

    // inches
    private final double goalPos;
    private final double errorThreshold = 2;
    private double error;
    private double CRUISE_VELOCITY = 15000;
    private LibTimer timer = new LibTimer(.5);

    private boolean isInitialized = false;

    public DriveForward(Length goalPos) {
        this.goalPos = goalPos.toInches();
        requires(Robot.drive);
    }

    @Override
    public void initialize() {
        // TODO create a CommandBase object to handle initialization
        isInitialized = true;
        Robot.drive.reset();
        Robot.drive.setHighGear();
        Robot.drive.changeToBrakeMode();
        Robot.drive.setGoalPos(goalPos);
        Robot.drive.changeToMotionMagicMode();
    }

    @Override
    protected void execute() {
        if (!isInitialized) {
            initialize();
            isInitialized = true;
        }
        error = Robot.drive.getGoalPos() - Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos());

        Robot.drive.setLeftRight(-Robot.drive.inchesToEncoderCounts(Robot.drive.getGoalPos()),
                -Robot.drive.inchesToEncoderCounts(Robot.drive.getGoalPos()));
        Robot.drive.configLeftRightCruiseVelocity(CRUISE_VELOCITY, CRUISE_VELOCITY);

    }

    @Override
    protected boolean isFinished() {
        return timer.isConditionMaintained(Math.abs(error) < errorThreshold);
    }

    @Override
    public void end() {
        isInitialized = false;
    }

}
