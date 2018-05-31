package org.usfirst.frc.team3309.commands.subsystems.lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.lib.CommandEx;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftElevate extends CommandEx {

    private double goalPos;
    private final double ERROR_THRESHOLD = 400;
    private double timeout = Double.POSITIVE_INFINITY;
    private double start = 0;

    public LiftElevate(double goalPos) {
        this.goalPos = goalPos;
        requires(Robot.lift);
    }

    public LiftElevate(double goalPos, double timeout) {
        this(goalPos);
        this.timeout = timeout;
    }

    public LiftElevate(AssemblyLocation assemblyLocation, double timeout) {
        this(assemblyLocation.getElevatorPosition());
        this.timeout = timeout;
    }

    @Override
    public void initialize() {
        super.initialize();
        System.out.println("Lift at " + Robot.lift.getPosition());
        Robot.lift.changeToBrakeMode();
        Robot.lift.setGoalPos(goalPos);
        Robot.lift.changeToPositionMode();
        start = Timer.getFPGATimestamp();
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



        if ((Timer.getFPGATimestamp() - start) > timeout) {
            System.out.println("Timeout hit!!");
            return true;
        }
        if (Math.abs(Robot.lift.getPosition() - goalPos) < ERROR_THRESHOLD) {
            System.out.println("Lift in range!!");
            return true;
        }
        return false;
    }

    @Override
    public void end() {
        super.end();
        System.out.println("The lift finished! " + Robot.lift.getPosition());
        start = Double.POSITIVE_INFINITY;
    }

}
