package org.usfirst.frc.team3309.commands.subsystems.rollers;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

public class RollersTeleop extends Command {

    private final double MIN_POWER = 0.1;
    private final double MAX_OUT_POWER = 0.7;
    private final double DEFAULT_POWER = 0.0;

    public RollersTeleop() {
        requires(Robot.rollers);
    }

    @Override
    public void initialize() {
        Robot.rollers.setLeftRight(0, 0);
    }

    @Override
    protected void execute() {
        double leftTrigger = OI.operatorRemote.leftTrigger.getY();
        double rightTrigger = OI.operatorRemote.rightTrigger.getY();

        if (Math.abs(leftTrigger) > MIN_POWER) {
                Robot.rollers.setLeftRight(-rescale(leftTrigger)* MAX_OUT_POWER, rescale(leftTrigger)*MAX_OUT_POWER);
        } else if (Math.abs(rightTrigger) > MIN_POWER) {
                Robot.rollers.setLeftRight(rescale(rightTrigger),-rescale(rightTrigger));
        } else {
            if (Robot.beltBar.isCubePresent() && Robot.arms.isArmsClosed()) {
                Robot.rollers.setLeftRight(DEFAULT_POWER, -DEFAULT_POWER);
            } else {
                Robot.rollers.setLeftRight(0, 0);
            }
        }

    }

    private double rescale(double val)
    {
        return Math.copySign(val*val,val);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
