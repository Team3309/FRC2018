package org.usfirst.frc.team3309.commands.subsystems.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.CommandEx;
import org.usfirst.frc.team3309.robot.Robot;

public class ShooterForward extends CommandEx {

    private double startTime;

    public ShooterForward() {
        requires(Robot.shooter);
    }

    @Override
    public void start() {
        super.start();
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    protected void execute() {
        Robot.shooter.actuateForward();
    }

    @Override
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime > 0.5;
    }

}
