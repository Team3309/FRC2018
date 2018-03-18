package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Constants;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftHybridMove extends Command {
    private double goalAngle;
    private double error;
    private double last = 0;
    private boolean started = false;
    public static final double ERROR_THRESHOLD = 100;

    public LiftHybridMove(double goalAngle) {
        this.goalAngle = goalAngle;
        requires(Robot.lift);
    }

    @Override
    protected void initialize() {
        Robot.lift.changeToPositionMode();
        Robot.lift.changeToBrakeMode();
    }

    @Override
    protected void execute() {
        double now = Timer.getFPGATimestamp();
        if (!started) {
            last = Timer.getFPGATimestamp();
            started = true;
        }
        double offset = 100 * (Timer.getFPGATimestamp() - last) * OI.operatorRemote.leftStick.getY();
        if (goalAngle + offset > Robot.lift.FORWARD_LIM) {
            goalAngle = Robot.lift.FORWARD_LIM;
        } else if (goalAngle + offset < 0) {
            goalAngle = 0;
        } else {
            goalAngle += offset;
        }
        Robot.lift.setGoalPos(goalAngle);
        Robot.lift.set(goalAngle);
        last = now;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        super.end();
        started = false;
    }
}
