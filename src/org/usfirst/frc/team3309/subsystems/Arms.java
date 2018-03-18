package org.usfirst.frc.team3309.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.robot.Constants;

public class Arms extends Subsystem {

    private DoubleSolenoid actuator = new DoubleSolenoid(Constants.ARMS_ACTUATOR_A,
            Constants.ARMS_ACTUATOR_B);

    @Override
    protected void initDefaultCommand() {
    }

    public void sendToDashboard() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("Arms");
        table.getEntry("arms closed: ").setBoolean(isArmsClosed());
    }

    private void setActuator(DoubleSolenoid.Value value) {
        actuator.set(value);
    }

    public void openArms() {
        setActuator(DoubleSolenoid.Value.kForward);
    }

    public void clampArms() {
        setActuator(DoubleSolenoid.Value.kReverse);
    }

    public boolean isArmsClosed() {
        return actuator.get() == DoubleSolenoid.Value.kReverse;
    }

}
