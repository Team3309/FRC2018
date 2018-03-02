package org.usfirst.frc.team3309.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.robot.Constants;

public class Arms extends Subsystem {

    private DoubleSolenoid leftActuator = new DoubleSolenoid(Constants.ARMS_LEFT_ACTUATOR_A,
            Constants.ARMS_LEFT_ACTUATOR_B);

    private DoubleSolenoid rightActuator = new DoubleSolenoid(Constants.ARMS_RIGHT_ACTUATOR_A,
            Constants.ARMS_RIGHT_ACTUATOR_B);

    @Override
    protected void initDefaultCommand() {
    }

    public void sendToDashboard() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("Arms");
        table.getEntry("arms closed: ").setBoolean(isArmsClosed());
    }

    private void setRightActuator(DoubleSolenoid.Value value) {
        rightActuator.set(value);
    }

    private void setLeftActuator(DoubleSolenoid.Value value) {
        leftActuator.set(value);
    }

    public void openArms() {
        setLeftActuator(DoubleSolenoid.Value.kForward);
        setRightActuator(DoubleSolenoid.Value.kForward);
    }

    public void clampArms() {
        setLeftActuator(DoubleSolenoid.Value.kReverse);
        setRightActuator(DoubleSolenoid.Value.kReverse);
    }

    public boolean isArmsClosed() {
        return leftActuator.get() == DoubleSolenoid.Value.kReverse
                && rightActuator.get() == DoubleSolenoid.Value.kReverse;
    }

}
