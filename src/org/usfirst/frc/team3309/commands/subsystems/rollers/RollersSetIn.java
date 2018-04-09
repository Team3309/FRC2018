package org.usfirst.frc.team3309.commands.subsystems.rollers;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class RollersSetIn extends InstantCommand {

    private boolean enable;

    public RollersSetIn(boolean enable) {
        requires(Robot.rollers);
        this.enable = enable;
    }

    @Override
    protected void execute() {
        super.execute();
        Robot.rollers.setEnableRollerPower(enable);
    }
}
