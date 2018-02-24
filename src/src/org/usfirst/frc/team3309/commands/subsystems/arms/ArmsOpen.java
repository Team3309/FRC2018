package src.org.usfirst.frc.team3309.commands.subsystems.arms;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class ArmsOpen extends Command {

    private double startTime;

    public ArmsOpen() {
        requires(Robot.arms);
    }

    @Override
    public void start() {
        super.start();
        startTime = Timer.getFPGATimestamp();
    }

    protected void execute() {
        Robot.arms.openArms();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
