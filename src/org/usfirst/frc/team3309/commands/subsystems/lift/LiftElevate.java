package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDController;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftElevate extends Command {

    private PIDController pidController;
    private double goalPos;

    public LiftElevate(double goalPos) {
        pidController = new PIDController(new PIDConstants(0.00009 ,0.0001,0));
        this.goalPos = goalPos;
        requires(Robot.lift);
    }

    @Override
    protected void initialize() {
        Robot.lift.changeToBrakeMode();
        Robot.lift.setGoalPos(goalPos);
        //Robot.lift.changeToPositionMode();
        Robot.lift.changeToPercentMode();
    }

    @Override
    protected void execute() {
        double power = pidController.update(Robot.lift.getPosition(), Robot.lift.getGoalPos());
        Robot.lift.set(power);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
