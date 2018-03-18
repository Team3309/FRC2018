package org.usfirst.frc.team3309.commands.subsystems.lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.CommandEx;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftElevate extends CommandEx {

    private double goalPos;
    private final double ERROR_THRESHOLD = 300;


    public LiftElevate(double goalPos) {
        this.goalPos = goalPos;
        requires(Robot.lift);
    }

    @Override
    public void initialize() {
        super.initialize();
        Robot.lift.changeToBrakeMode();
        Robot.lift.setGoalPos(goalPos);
        Robot.lift.changeToPositionMode();
    }

    @Override
    protected void execute() {

        if (goalPos > Robot.lift.getFORWARD_LIM()) {
            goalPos = 0;
        }

        if (LiftFindZero.isZeroed()) {
            Robot.lift.set(goalPos);
        } else {
            Robot.lift.set(ControlMode.Disabled,0);
        }

    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.lift.getError()) < ERROR_THRESHOLD;
    }

    @Override
    public void end() {
        super.end();
    }

}
