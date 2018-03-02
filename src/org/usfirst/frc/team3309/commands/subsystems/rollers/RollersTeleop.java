package org.usfirst.frc.team3309.commands.subsystems.rollers;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

public class RollersTeleop extends Command {

    private final double MIN_POWER = 0.1;
    private final double MAX_POWER= 0.5;

    private final double DEFAULT_POWER = 0.1;

    public RollersTeleop() {
        requires(Robot.rollers);
    }

    @Override
    protected void execute() {
        double leftTrigger = OI.operatorRemote.leftTrigger.getY();
        double rightTrigger = OI.operatorRemote.rightTrigger.getY();
        if (Math.abs(leftTrigger) > MIN_POWER) {
            if (Math.abs(leftTrigger) > MAX_POWER) {
                Robot.rollers.setLeftRight(-MAX_POWER, MAX_POWER);
            } else {
                Robot.rollers.setLeftRight(-leftTrigger, leftTrigger);
            }
        } else if (Math.abs(rightTrigger) > MIN_POWER) {
            if (Math.abs(rightTrigger) > MAX_POWER) {
                Robot.rollers.setLeftRight(MAX_POWER, -MAX_POWER);
            } else {
                Robot.rollers.setLeftRight(rightTrigger, -rightTrigger);
            }
        } else {
            Robot.rollers.setLeftRight(DEFAULT_POWER,-DEFAULT_POWER);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
