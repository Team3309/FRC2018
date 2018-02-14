package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.math.LibMath;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

@Deprecated
public class LiftManualTest extends Command {

    private final double MAX_POWER = 0.5;

    public LiftManualTest() {
        requires(Robot.lift);
    }

    @Override
    protected void initialize() {
        Robot.lift.changeToPercentMode();
    }

    @Override
    protected void execute() {
        double power = LibMath.handleDeadband(OI.operatorRemote.getJoystick().getY(GenericHID.Hand.kLeft), MAX_POWER);
        Robot.lift.set(power);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
