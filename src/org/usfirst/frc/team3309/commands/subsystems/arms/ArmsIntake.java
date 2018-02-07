package org.usfirst.frc.team3309.commands.subsystems.arms;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class ArmsIntake extends InstantCommand {

    public ArmsIntake() {
        requires(Robot.arms);
    }

    @Override
    protected void execute() {
        Robot.arms.setLeftActuator(DoubleSolenoid.Value.kForward);
        Robot.arms.setRightActuator(DoubleSolenoid.Value.kReverse);
    }

}
