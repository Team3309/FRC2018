package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDController;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveTurn extends Command {

    private double goalAngle;
    private double error;
    private PIDController angleController;

    public DriveTurn(double goalAngle) {
        this.goalAngle = goalAngle;
        angleController = new PIDController(new PIDConstants(0.006, 0, 0.004));
        requires(Robot.drive);
    }

    @Override
    public void start() {
        super.start();
        Robot.drive.reset();
        Robot.drive.changeToBrakeMode();
        Robot.drive.changeToPercentMode();
    }

    @Override
    protected void execute() {
        System.out.println("Drive angle " + Robot.drive.getAngPos());
        error = goalAngle - Robot.drive.getAngPos();
        System.out.println("Error: " + error);
        double power = angleController.update(Robot.drive.getAngPos(), goalAngle);
        if (goalAngle > 0 ) {
            Robot.drive.setLeftRight(power, -power);
        } else {
            Robot.drive.setLeftRight(power, -power);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
