package src.org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.lib.controllers.pid.PIDController;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveTurn extends Command {

    private double goalAngle;
    private double error;
    private PIDController angleController;
    private final double ANGLE_LENIENCY = 5;

    public DriveTurn(double goalAngle) {
        this.goalAngle = goalAngle;
        angleController = new PIDController(new PIDConstants(0.0077, 0, 0.00001));
        requires(Robot.drive);
    }

    @Override
    public void start() {
        super.start();
        Robot.drive.reset();
        Robot.drive.setHighGear();
        Robot.drive.changeToBrakeMode();
        Robot.drive.changeToPercentMode();
    }

    @Override
    protected void execute() {
        System.out.println("Drive angle " + Robot.drive.getAngPos());
        error = goalAngle - Robot.drive.getAngPos();
        System.out.println("Error: " + error);
        double power = angleController.update(Robot.drive.getAngPos(), goalAngle);
        Robot.drive.setLeftRight(power, -power);
    }

    @Override
    protected boolean isFinished() {
        return isWithin(Robot.drive.getAngPos(), goalAngle - ANGLE_LENIENCY, goalAngle + ANGLE_LENIENCY);
    }

    private boolean isWithin(double val, double a, double b) {
        if (a < b) {
            return val > a && val < b;
        }
        return val < a && val > b;
    }

}
