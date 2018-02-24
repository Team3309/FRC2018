package src.org.usfirst.frc.team3309.commands.subsystems.shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class ShooterBack extends Command {

    public ShooterBack() {
        requires(Robot.shooter);
    }

    @Override
    protected void execute() {
        Robot.shooter.actuateBack();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
