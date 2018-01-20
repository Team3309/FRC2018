package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftElevate extends Command {

    private double goalPos;

    public LiftElevate(double goalPos) {
        this.goalPos = goalPos;
        requires(Robot.lift);
    }

    @Override
    protected void initialize() {
        Robot.lift.setGoalPos(goalPos);
    }

    @Override
    protected void execute() {
        Robot.lift.setLift(Robot.lift.getGoalPos());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
