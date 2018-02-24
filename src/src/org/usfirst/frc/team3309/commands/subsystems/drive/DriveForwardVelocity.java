package src.org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class DriveForwardVelocity extends Command {

    private double goalVelocity;

    public DriveForwardVelocity(double goalVelocity) {
        requires(Robot.drive);
        this.goalVelocity = goalVelocity;
    }

    @Override
    protected void initialize() {
        Robot.drive.changeToVelocityMode();
        Robot.drive.setHighGear();
        Robot.drive.changeToBrakeMode();
    }

    @Override
    protected void execute() {
        Robot.drive.setLeftRight(goalVelocity, goalVelocity);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
