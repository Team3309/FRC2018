package org.usfirst.frc.team3309.commands.subsystems.intake;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.driverstation.Controls;
import org.usfirst.frc.team3309.robot.Robot;

public class IntakeTeleop extends Command {

    private final double MIN_POWER = 0.52;

    public IntakeTeleop() {
        requires(Robot.intake);
    }

    @Override
    protected void execute() {
        double leftTrigger = Controls.operatorRemote.leftTrigger.getX();
        double rightTrigger = Controls.operatorRemote.rightTrigger.getX();
        if (Math.abs(leftTrigger) > MIN_POWER) {
            Robot.intake.setLeftRightRoller(leftTrigger, leftTrigger);
        } else {
            Robot.intake.setLeftRightRoller(-rightTrigger, -rightTrigger);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
