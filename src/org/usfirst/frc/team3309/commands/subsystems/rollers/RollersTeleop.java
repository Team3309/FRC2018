package org.usfirst.frc.team3309.commands.subsystems.rollers;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

public class RollersTeleop extends Command {

    private final double MIN_POWER = 0.1;
    private final double ROLLER_FEED_FORWARD = 1/3;
    private final double DEFAULT_POWER = 0.1;

    public RollersTeleop() {
        requires(Robot.rollers);
    }

    @Override
    protected void execute() {
        double leftTrigger = OI.operatorRemote.leftTrigger.getY();
        double rightTrigger = OI.operatorRemote.rightTrigger.getY();

        if (Math.abs(leftTrigger) > MIN_POWER) {
                Robot.rollers.setLeftRight(-rescale(leftTrigger), rescale(leftTrigger));
        } else if (Math.abs(rightTrigger) > MIN_POWER) {
                Robot.rollers.setLeftRight(rescale(rightTrigger),-rescale(rightTrigger));
        } else {
            if (Robot.beltBar.isCubePresent() && Robot.arms.isArmsClosed()) {
                Robot.rollers.setLeftRight(-DEFAULT_POWER, DEFAULT_POWER);
            } else {
                Robot.rollers.setLeftRight(0, 0);
            }
        }
    }

    private double rescale(double val)
    {
        return Math.max(val+ROLLER_FEED_FORWARD,1);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
