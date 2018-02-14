package org.usfirst.frc.team3309.commands.subsystems.beltbar;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.math.LibMath;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

@Deprecated
public class BeltBarManualTest extends Command {

    private final double MAX_POWER = 0.8;

    public BeltBarManualTest() {
        requires(Robot.beltBar);
    }

    @Override
    protected void initialize() {
        Robot.beltBar.changeToPercentMode();
    }

    @Override
    protected void execute() {
        double power = LibMath.handleDeadband(OI.operatorRemote.getJoystick().getY(GenericHID.Hand.kRight), MAX_POWER);
        Robot.beltBar.set(power);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
