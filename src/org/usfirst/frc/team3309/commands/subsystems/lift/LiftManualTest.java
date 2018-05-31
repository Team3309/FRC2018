package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

@Deprecated
public class LiftManualTest extends Command {

    private final double MAX_POWER = 1.0;

    public LiftManualTest() {
        requires(Robot.lift);
    }

    @Override
    protected void initialize() {
        Robot.lift.changeToPercentMode();
    }

    @Override
    protected void execute() {
        double power = OI.operatorRemote.leftStick.getY();
        if (Math.abs(power) > MAX_POWER) {
            power = MAX_POWER * Math.signum(power);
        }
        Robot.lift.set(power);
        SmartDashboard.putNumber("Lift power: ", power);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}