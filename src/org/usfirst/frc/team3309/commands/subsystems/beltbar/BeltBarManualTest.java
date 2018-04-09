package org.usfirst.frc.team3309.commands.subsystems.beltbar;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

@Deprecated
public class BeltBarManualTest extends Command {

    public BeltBarManualTest() {
        requires(Robot.beltBar);
    }

    @Override
    protected void initialize() {
        Robot.beltBar.changeToPercentMode();
    }

    @Override
    protected void execute() {
        double power = -OI.operatorRemote.rightStick.getY();
        Robot.beltBar.set(power);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
