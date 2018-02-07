package org.usfirst.frc.team3309.commands.subsystems.arms;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Robot;

public class ArmsClamp extends InstantCommand {

    public ArmsClamp() {
        requires(Robot.arms);
    }

    protected void execute() {
        Robot.arms.setLeftActuator(DoubleSolenoid.Value.kForward);
        Robot.arms.setRightActuator(DoubleSolenoid.Value.kForward);
    }

}
