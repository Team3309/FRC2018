package org.usfirst.frc.team3309.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.robot.Constants;
import org.usfirst.frc.team3309.robot.OI;
import org.usfirst.frc.team3309.robot.Robot;

public class Arms extends Subsystem {

    private DoubleSolenoid actuator = new DoubleSolenoid(Constants.ARMS_ACTUATOR_A,
            Constants.ARMS_ACTUATOR_B);

    @Override
    protected void initDefaultCommand() {
    }

    public void periodic() {
    }

    public void sendToDashboard() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("Arms");
        table.getEntry("arms closed: ").setBoolean(isArmsClosed());
    }

    public void openArms() {
        actuator.set(DoubleSolenoid.Value.kReverse);
    }

    public void clampArms() {
        actuator.set(DoubleSolenoid.Value.kForward);
    }

    public void middleArms() {
        actuator.set(DoubleSolenoid.Value.kForward);
    }

    public boolean isArmsClosed() {
        return actuator.get() == DoubleSolenoid.Value.kReverse;
    }

}
