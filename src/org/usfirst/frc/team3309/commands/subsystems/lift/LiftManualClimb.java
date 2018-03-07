package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftManualClimb extends Command {

    private final double MAX_POWER = 0.75;

    public LiftManualClimb() {
        requires(Robot.lift);
    }

    @Override
    protected void initialize() {
        Robot.lift.changeToPercentMode();
    }

    @Override
    protected void execute() {
        double power = -OI.operatorRemote.leftStick.getY();
        if (Math.abs(power) > MAX_POWER) {
            power = MAX_POWER * Math.signum(power);
        }
        if (Robot.lift.isAtBottom() && power > 0.1) {
            power = 0;
        }
        Robot.lift.set(power);
        SmartDashboard.putNumber("Lift power: ", power);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
