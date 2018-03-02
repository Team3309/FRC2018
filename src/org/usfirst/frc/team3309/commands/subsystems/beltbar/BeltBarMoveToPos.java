package org.usfirst.frc.team3309.commands.subsystems.beltbar;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class BeltBarMoveToPos extends Command {

    private final double goalAngle;
    private final double ERROR_THRESHOLD = 230;

    public BeltBarMoveToPos(double goalAngle) {
        this.goalAngle = goalAngle;
        requires(Robot.beltBar);
    }

    @Override
    protected void initialize() {
        Robot.beltBar.setGoalAngle(goalAngle);
        Robot.beltBar.changeToPositionMode();
    }

    @Override
    protected void execute() {
        Robot.beltBar.set(Robot.beltBar.getGoalAngle());
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.beltBar.getError()) < ERROR_THRESHOLD;
    }

}
