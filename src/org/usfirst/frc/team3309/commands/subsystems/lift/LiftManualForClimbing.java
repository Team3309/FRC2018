package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.math.LibMath;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftManualForClimbing extends Command {

    private final double MIN_POWER = 0.09;
    private final double MAX_POWER = 0.5;

    public LiftManualForClimbing() {
        requires(Robot.lift);
    }

    @Override
    public void initialize() {
        Robot.lift.changeToPositionMode();
    }

    @Override
    public void execute() {
        double power = LibMath.handleDeadband(OI.operatorRemote.leftStick.getY(), MIN_POWER);
  /*      if (Math.abs(power) < MIN_POWER) {
            Robot.lift.changeToPositionMode();
        } else {
            Robot.lift.changeToPercentMode();
            if (Robot.lift.getLiftPos() < -10 && power < 0) {
                Robot.lift.set(0);
            } else if (Robot.lift.getLiftPos() > MAX_LIFT_HEIGHT && power > 0) {
                Robot.lift.changeToPositionMode();
            } else {
                if (Math.abs(power) > MAX_POWER) {
                    power = Math.signum(power) * MAX_POWER;
                }
                Robot.lift.set(power);
            }
        }*/
        if (OI.operatorRemote.leftStick.get() && power < Math.abs(MIN_POWER)) {
            Robot.lift.changeToPositionMode();
        } else if (Math.abs(power) > 0){
            Robot.lift.changeToPercentMode();
            if (Robot.lift.getLiftPos() < -10 && power < 0) {
                Robot.lift.set(0);
            } else {
                if (Math.abs(power) > MAX_POWER) {
                    power = Math.signum(power) * MAX_POWER;
                }
                Robot.lift.set(power);
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
