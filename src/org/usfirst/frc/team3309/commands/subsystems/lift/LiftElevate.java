package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftElevate extends Command {

    private double goalPos;
    private final double ERROR_THRESHOLD = 300;

    public LiftElevate(double goalPos) {
        this.goalPos = goalPos;
        requires(Robot.lift);
    }

    @Override
    protected void initialize() {
        Robot.lift.changeToBrakeMode();
        Robot.lift.setGoalPos(goalPos);
        Robot.lift.changeToPositionMode();
    }

    @Override
    protected void execute() {
        if (goalPos > Robot.lift.getFORWARD_LIM()) {
            goalPos = 0;
        }

        if (LiftCheckLimits.isZeroed()) {
            Robot.lift.set(goalPos);
        } else {
            Robot.lift.changeToPercentMode();
            Robot.lift.set(0);
        }

    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.lift.getError()) < ERROR_THRESHOLD;
    }

}
