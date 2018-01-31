package org.usfirst.frc.team3309.commands.subsystems.intake;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class IntakeAccept extends Command {

    private final double intakeRollerPower;

    public IntakeAccept(double intakeRollerPower) {
        this.intakeRollerPower = intakeRollerPower;
        requires(Robot.intake);
    }

    @Override
    protected void execute() {
        Robot.intake.setLeftRightRoller(intakeRollerPower, intakeRollerPower);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
