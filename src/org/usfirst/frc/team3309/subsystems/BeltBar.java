package org.usfirst.frc.team3309.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.lib.actuators.TalonSRXMC;
import org.usfirst.frc.team3309.lib.actuators.VictorSPXMC;
import org.usfirst.frc.team3309.robot.RobotMap;

public class BeltBar extends Subsystem {

    private TalonSRXMC masterBar = new TalonSRXMC(RobotMap.BELTBAR_0);
    private VictorSPXMC slaveBar = new VictorSPXMC(RobotMap.BELTBAR_1);

    private double goalAngle;

    public BeltBar() {
        masterBar.changeControlMode(CANTalon.TalonControlMode.Position);
        slaveBar.changeControlMode(CANTalon.TalonControlMode.Follower);
        slaveBar.set(masterBar.getDeviceID());
        masterBar.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public double getBarAngle() {
        return masterBar.getAnalogInPosition();
    }

    public void changeToPercentMode() {
        masterBar.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }
    
    public void setBar(double power) {
        masterBar.set(power);
    }

    public double getGoalAngle() {
        return goalAngle;
    }

    public void setGoalAngle(double goalAngle) {
        this.goalAngle = goalAngle;
    }
}
