package org.usfirst.frc.team3309.commands.subsystems.rollers;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class RollersAccept extends Command {

    private final double intakeRollerPower;

    public RollersAccept(double intakeRollerPower) {
        this.intakeRollerPower = intakeRollerPower;
        requires(Robot.rollers);
    }

    @Override
    protected void execute() {
        Robot.rollers.setLeftRight(intakeRollerPower, intakeRollerPower);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
