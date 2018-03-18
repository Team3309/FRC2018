package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftManualForClimbing extends Command {

    private final double MAX_POWER = 0.775;

    public LiftManualForClimbing() {
        requires(Robot.lift);
    }

    @Override
    public void initialize() {
        Robot.lift.changeToPercentMode();
    }

    @Override
    public void execute() {
        double power = OI.operatorRemote.leftStick.getY();
       if (Math.abs(power) > MAX_POWER) {
           power = Math.signum(power) * MAX_POWER;
       }
        Robot.lift.set(power);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
