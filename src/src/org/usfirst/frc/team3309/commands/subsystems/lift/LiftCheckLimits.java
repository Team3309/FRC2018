package src.org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftCheckLimits extends Command {

    @Override
    protected void execute() {
        if (Robot.lift.isBottomLimitSwitch()) {
            Robot.lift.resetToBottom();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
