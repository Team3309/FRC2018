package org.usfirst.frc.team3309.commands.subsystems.shooter;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class ShooterBack extends InstantCommand {

    public ShooterBack() {
        requires(Robot.shooter);
    }

    @Override
    protected void execute() {
        Robot.shooter.actuateBack();
    }

}
