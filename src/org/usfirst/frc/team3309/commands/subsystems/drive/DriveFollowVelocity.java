package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.LibTimer;
import org.usfirst.frc.team3309.lib.controllers.helpers.VelocityChangePoint;
import org.usfirst.frc.team3309.robot.Robot;

import java.util.ArrayList;

/*
* not working
* */
@Deprecated
public class DriveFollowVelocity extends Command {

    private ArrayList<VelocityChangePoint> velocityChangePoints;

    private LibTimer libTimer = new LibTimer(0.5);
    private double posGoal;

    public DriveFollowVelocity(ArrayList<VelocityChangePoint> velocityChangePoints, double posGoal) {
        this.velocityChangePoints = velocityChangePoints;
        this.posGoal = posGoal;
    }

    @Override
    public void initialize() {
        Robot.drive.changeToVelocityMode();
        Robot.drive.changeToBrakeMode();
        Robot.drive.setHighGear();
    }

    @Override
    public void execute() {

        VelocityChangePoint curVelPoint = null;

        double curPos = Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos());
        double closestPoint = Double.POSITIVE_INFINITY;

        for (VelocityChangePoint curPoint : velocityChangePoints) {
            if (Math.abs(curPos) >= Math.abs(curPoint.getPosToChangeAt())) {
                if (Math.abs(Math.abs(curPos) - Math.abs(curPoint.getPosToChangeAt())) <= closestPoint) {
                    curVelPoint = curPoint;
                    closestPoint = Math.abs(Math.abs(curPos) - Math.abs(curPoint.getPosToChangeAt()));
                }
            }
        }

        System.out.println("curPos " + curPos);
        System.out.println(curVelPoint);

        if (curVelPoint != null) {
            Robot.drive.setLeftRight(curVelPoint.getLeftVel(), curVelPoint.getRightVel());
        }

    }

    @Override
    protected boolean isFinished() {
        return libTimer.isConditionMaintained(
                Math.abs(Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos())) > Math.abs(posGoal));
    }

}
