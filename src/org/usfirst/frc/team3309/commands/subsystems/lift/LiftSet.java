package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftSet extends Command {

    private double power;

    public LiftSet(double power) {
        this.power = power;
        requires(Robot.lift);
    }

    @Override
    protected void initialize() {
        Robot.lift.changeToPercentMode();
    }

    @Override
    public void start() {
        Robot.lift.set(power);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
