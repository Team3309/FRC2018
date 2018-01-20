package org.usfirst.frc.team3309.commands.subsystems.intake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class IntakeOpen extends InstantCommand {

    public IntakeOpen() {
        requires(Robot.intake);
    }

    protected void execute() {
        Robot.intake.setHardStopActuator(DoubleSolenoid.Value.kReverse);
        Robot.intake.setInnerActuator(DoubleSolenoid.Value.kReverse);
    }


}
