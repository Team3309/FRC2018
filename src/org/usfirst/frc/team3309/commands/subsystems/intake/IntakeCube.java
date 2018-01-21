package org.usfirst.frc.team3309.commands.subsystems.intake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class IntakeCube extends InstantCommand {

    public IntakeCube() {
    }

    @Override
    protected void execute() {
        Robot.intake.setHardStopActuator(DoubleSolenoid.Value.kForward);
        Robot.intake.setInnerActuator(DoubleSolenoid.Value.kOff);
    }

}
