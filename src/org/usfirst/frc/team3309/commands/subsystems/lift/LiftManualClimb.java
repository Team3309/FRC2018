package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftManualClimb extends Command {

    private final double MIN_POWER = 0.1;

    public LiftManualClimb() {
        requires(Robot.lift);
    }

    @Override
    public void initialize() {
        Robot.lift.changeToPositionMode();
    }

    @Override
    public void execute() {
        double power = OI.operatorRemote.leftStick.getY();
        if (Math.abs(power) < MIN_POWER) {
            Robot.lift.changeToPositionMode();
        } else {
            Robot.lift.changeToPercentMode();
            Robot.lift.set(power);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
