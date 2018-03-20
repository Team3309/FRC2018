package org.usfirst.frc.team3309.commands.subsystems.beltbar;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.lib.LibTimer;
import org.usfirst.frc.team3309.robot.Constants;
import org.usfirst.frc.team3309.robot.Robot;

public class BeltBarMoveToPos extends Command {

    private final double goalAngle;
    private double error;
    public static final double ERROR_THRESHOLD = 100;
    private boolean hasStarted = false;

    private final double MIN_LIFT_POS_TO_ADJUST_HOME = 10000;
    private final double BELTBAR_GOAL_ADJUSTMENT = 400;

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
        if (Robot.lift.getPosition() > MIN_LIFT_POS_TO_ADJUST_HOME &&
                Math.abs(goalAngle - Constants.BELTBAR_BOTTOM_POS) < 10.0 && !hasStarted) {
            Robot.beltBar.set(goalAngle + BELTBAR_GOAL_ADJUSTMENT);
            hasStarted = true;
            System.out.println("i have changed");
        } else {
            Robot.beltBar.set(goalAngle);
            hasStarted = false;
        }
        SmartDashboard.putNumber("Beltbar goal" , goalAngle);
        error = goalAngle - Robot.beltBar.getPosition();
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(error) < ERROR_THRESHOLD;
    }

    @Override
    public void end() {
        hasStarted = false;
    }

}
