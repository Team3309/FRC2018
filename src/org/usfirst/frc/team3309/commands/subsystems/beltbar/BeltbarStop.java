package org.usfirst.frc.team3309.commands.subsystems.beltbar;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class BeltbarStop extends Command {

    public BeltbarStop() {
        requires(Robot.beltBar);
    }

    @Override
    protected void execute() {
        Robot.beltBar.set(ControlMode.Disabled, 0);
        Robot.beltBar.disableLimits();
        Robot.beltBar.setIsManual(true);
        Robot.beltBar.setDefaultCommand(new BeltBarManualTest());
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
