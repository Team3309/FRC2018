package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftElevate extends Command {

    private double goalPos;

    public LiftElevate(Length goalPos) {
        this.goalPos = goalPos.toInches();
        requires(Robot.lift);
    }

    @Override
    protected void initialize() {
        Robot.lift.changeToBrakeMode();
        Robot.lift.setGoalPos(Robot.lift.inchesToEncoderCounts(goalPos));
    }

    @Override
    protected void execute() {
        Robot.lift.setPosition(Robot.lift.getGoalPos());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void interrupted() {
        Robot.lift.setGoalPos(0);
    }

}
