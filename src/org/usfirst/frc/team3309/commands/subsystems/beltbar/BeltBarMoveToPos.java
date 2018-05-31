package org.usfirst.frc.team3309.commands.subsystems.beltbar;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.LibTimer;
import org.usfirst.frc.team3309.robot.Constants;
import org.usfirst.frc.team3309.robot.Robot;

public class BeltBarMoveToPos extends Command {

    private double goalAngle;
    private double error;
    public static final double ERROR_THRESHOLD = 250;
    private boolean hasStarted = false;

    private final double MIN_LIFT_POS_TO_ADJUST_HOME = 10000;
    private final double BELTBAR_GOAL_ADJUSTMENT = 400;

    private LibTimer timer = new LibTimer();

    public BeltBarMoveToPos(double goalAngle) {
        this.goalAngle = goalAngle;
        requires(Robot.beltBar);
        setInterruptible(true);
    }

    @Override
    protected void initialize() {
        if (!Robot.beltBar.getIsManual()) {
            Robot.beltBar.setGoalAngle(goalAngle);
            Robot.beltBar.changeToPositionMode();
            Robot.beltBar.changeToBrakeMode();
            timer.start();
        }
    }

    @Override
    protected void execute() {
        if (!Robot.beltBar.getIsManual()) {
            if (Robot.lift.getPosition() > MIN_LIFT_POS_TO_ADJUST_HOME &&
                    Math.abs(goalAngle - Constants.BELTBAR_BOTTOM_POS) < 10.0 && !hasStarted) {
                double newAngle = goalAngle + BELTBAR_GOAL_ADJUSTMENT;
                Robot.beltBar.set(newAngle);
                hasStarted = true;
            } else {
                Robot.beltBar.set(goalAngle);
                hasStarted = false;
            }
            error = goalAngle - Robot.beltBar.getPosition();
        } else {
            new BeltBarManualTest().start();
        }
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(error) < ERROR_THRESHOLD || Robot.beltBar.getIsManual();
    }

    @Override
    public void end() {
        System.out.println("The beltbar Finished!");
        hasStarted = false;
    }

}
