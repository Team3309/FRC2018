package org.usfirst.frc.team3309.commands.subsystems.beltbar;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class BeltBarMoveToPos extends Command {

    private final double goalPos;

    public BeltBarMoveToPos(double goalPos) {
        this.goalPos = goalPos;
        requires(Robot.beltBar);
    }

    @Override
    protected void initialize() {
        Robot.lift.setGoalPos(goalPos);
    }

    @Override
    protected void execute() {
        Robot.beltBar.setBar(Robot.beltBar.getGoalAngle());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
