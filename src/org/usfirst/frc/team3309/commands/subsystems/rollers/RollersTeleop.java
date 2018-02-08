package org.usfirst.frc.team3309.commands.subsystems.rollers;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

public class RollersTeleop extends Command {

    private final double MIN_POWER = 0.52;

    public RollersTeleop() {
        requires(Robot.rollers);
    }

    @Override
    protected void execute() {
        double leftTrigger = OI.operatorRemote.leftTrigger.getX();
        double rightTrigger = OI.operatorRemote.rightTrigger.getX();
        if (Math.abs(leftTrigger) > MIN_POWER) {
            Robot.rollers.setLeftRight(leftTrigger, leftTrigger);
        } else {
            Robot.rollers.setLeftRight(-rightTrigger, -rightTrigger);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
