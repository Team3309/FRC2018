package src.org.usfirst.frc.team3309.commands.subsystems.beltbar;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class BeltBarMoveToPos extends Command {

    private final double goalAngle;

    public BeltBarMoveToPos(double goalAngle) {
        this.goalAngle = goalAngle;
        requires(Robot.beltBar);
    }

    @Override
    protected void initialize() {
        Robot.beltBar.setGoalAngle(goalAngle);
        Robot.beltBar.changeToBrakeMode();
        Robot.beltBar.changeToPositionMode();
    }

    @Override
    protected void execute() {
        Robot.beltBar.set(Robot.beltBar.getGoalAngle());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
