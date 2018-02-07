package org.usfirst.frc.team3309.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsIntake;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsOpen;
import org.usfirst.frc.team3309.robot.Constants;

public class Arms extends Subsystem {

    private DoubleSolenoid leftActuator = new DoubleSolenoid(Constants.ARMS_LEFT_ACTUATOR_A,
            Constants.ARMS_LEFT_ACTUATOR_B);

    private DoubleSolenoid rightActuator = new DoubleSolenoid(Constants.ARMS_RIGHT_ACTUATOR_A,
            Constants.ARMS_RIGHT_ACTUATOR_B);

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ArmsIntake());
    }

    public void setRightActuator(DoubleSolenoid.Value value) {
        rightActuator.set(value);
    }

    public void setLeftActuator(DoubleSolenoid.Value value) {
        leftActuator.set(value);
    }

}
