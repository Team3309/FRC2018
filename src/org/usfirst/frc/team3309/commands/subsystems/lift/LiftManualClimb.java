package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftManualClimb extends Command {

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
        Robot.lift.set(power);
        SmartDashboard.putNumber("Lift power: ", power);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
