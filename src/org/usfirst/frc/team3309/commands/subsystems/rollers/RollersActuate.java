package org.usfirst.frc.team3309.commands.subsystems.rollers;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class RollersActuate extends Command {

    private double time;
    private double start;
    private boolean isInit = false;
    private final double intakeRollerPower;

    public RollersActuate(double intakeRollerPower, double time) {
        this.intakeRollerPower = intakeRollerPower;
        this.time = time;
        requires(Robot.rollers);
    }

    @Override
    public void initialize() {
        start = Timer.getFPGATimestamp();
        isInit = true;
    }

    @Override
    protected void execute() {
        if (!isInit) {
            initialize();
        }
        Robot.rollers.setLeftRight(-intakeRollerPower, intakeRollerPower);
    }

    @Override
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - start >= time;
    }

    @Override
    public void end() {
        isInit = false;
        start = Double.POSITIVE_INFINITY;
        Robot.rollers.setLeftRight(0, 0);
    }
}
