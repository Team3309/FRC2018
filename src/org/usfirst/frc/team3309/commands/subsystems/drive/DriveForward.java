package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDController;
import org.usfirst.frc.team3309.robot.Robot;

import javax.xml.bind.SchemaOutputResolver;

public class DriveForward extends Command {

    // inches
    private final double goalPos;
    private final double errorThreshold = 3;
    private double error;
    private final double CRUISE_VELOCITY = 15000; // 20000
    private PIDController turningController;

    public DriveForward(Length goalPos) {
        this.goalPos = goalPos.toInches();
        requires(Robot.drive);
        turningController = new PIDController(new PIDConstants(0.0, 0, 0));
    }

    @Override
    public void start() {
        super.start();
        Robot.drive.reset();
        Robot.drive.setHighGear();
        Robot.drive.changeToBrakeMode();
        Robot.drive.setGoalPos(goalPos);
        Robot.drive.changeToMotionMagicMode();
       /* Robot.drive.configLeftRightCruiseVelocity(CRUISE_VELOCITY, CRUISE_VELOCITY);
        Robot.drive.setLeftRight(Robot.drive.inchesToEncoderCounts(Robot.drive.getGoalPos()),
                Robot.drive.inchesToEncoderCounts(Robot.drive.getGoalPos()));*/
    }
    
    @Override
    protected void execute() {
        double adjustmentVelocity = turningController.update(Robot.drive.getAngPos(), 0.0);
        error = Robot.drive.getGoalPos() - Robot.drive.encoderCountsToInches(Robot.drive.getEncoderPos());
        Robot.drive.setLeftRight(-Robot.drive.inchesToEncoderCounts(Robot.drive.getGoalPos()),
                -Robot.drive.inchesToEncoderCounts(Robot.drive.getGoalPos()));
        Robot.drive.configLeftRightCruiseVelocity(CRUISE_VELOCITY, CRUISE_VELOCITY);
        System.out.println("drive error " + error);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(error) < errorThreshold;
    }

}
