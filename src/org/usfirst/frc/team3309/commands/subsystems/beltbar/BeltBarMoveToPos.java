package org.usfirst.frc.team3309.commands.subsystems.beltbar;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.lib.LibTimer;
import org.usfirst.frc.team3309.robot.Robot;

public class BeltBarMoveToPos extends Command {

    private final double goalAngle;
    private double error;
    public static final double ERROR_THRESHOLD = 100;

    private LibTimer timer = new LibTimer();

    public BeltBarMoveToPos(double goalAngle) {
        this.goalAngle = goalAngle;
        requires(Robot.beltBar);
    }

    @Override
    protected void initialize() {
        Robot.beltBar.setGoalAngle(goalAngle);
        Robot.beltBar.changeToPositionMode();
        Robot.beltBar.changeToBrakeMode();
        timer.start();
    }

    @Override
    protected void execute() {
        Robot.beltBar.set(goalAngle);
        error = goalAngle - Robot.beltBar.getPosition();
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(error) < ERROR_THRESHOLD;
    }

}
