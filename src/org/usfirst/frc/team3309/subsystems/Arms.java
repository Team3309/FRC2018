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

    private Solenoid otherActuator = new Solenoid(Constants.ARMS_OTHER_ACTUATOR);

    @Override
    protected void initDefaultCommand() {
    }

    public void sendToDashboard() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("Arms");
        table.getEntry("arms closed: ").setBoolean(isArmsClosed());
    }

    public void openArms() {
        actuator.set(DoubleSolenoid.Value.kReverse);
      //  otherActuator.set(false);
    }

    public void clampArms() {
        actuator.set(DoubleSolenoid.Value.kForward);
     //   otherActuator.set(true);
    }

    public void middleArms() {
        actuator.set(DoubleSolenoid.Value.kForward);
      //  otherActuator.set(true);
    }

    public boolean isArmsClosed() {
        return actuator.get() == DoubleSolenoid.Value.kReverse;
    }

}
