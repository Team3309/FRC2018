package org.usfirst.frc.team3309.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import lib.actuators.TalonSRXMC;
import lib.actuators.VictorSPXMC;
import org.usfirst.frc.team3309.robot.RobotMap;

public class BeltBar extends Subsystem {

    private TalonSRXMC masterBar = new TalonSRXMC(RobotMap.BELTBAR_0);
    private VictorSPXMC slaveBar = new VictorSPXMC(RobotMap.BELTBAR_1);

    public BeltBar() {
        masterBar.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        slaveBar.changeControlMode(CANTalon.TalonControlMode.Follower);
        slaveBar.set(masterBar.getDeviceID());
        masterBar.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void changeToPercentMode() {
        masterBar.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }

    public void changeToVelocityMode() {
        masterBar.changeControlMode(CANTalon.TalonControlMode.Speed);
    }

    public void setBar(double power) {
        masterBar.set(power);
    }

}
