package org.usfirst.frc.team3309.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import lib.actuators.TalonSRXMC;
import lib.actuators.VictorSPXMC;
import org.usfirst.frc.team3309.robot.RobotMap;

public class Lift extends Subsystem {

    private TalonSRXMC lift0 = new TalonSRXMC(RobotMap.LIFT_0);
    private VictorSPXMC lift1 = new VictorSPXMC(RobotMap.LIFT_1);
    private VictorSPXMC lift2 = new VictorSPXMC(RobotMap.LIFT_2);
    private VictorSPXMC lift3 = new VictorSPXMC(RobotMap.LIFT_3);
    private VictorSPXMC lift4 = new VictorSPXMC(RobotMap.LIFT_4);


    @Override
    protected void initDefaultCommand() {
        lift0.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
        lift0.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        lift1.changeControlMode(CANTalon.TalonControlMode.Follower);
        lift2.changeControlMode(CANTalon.TalonControlMode.Follower);
        lift3.changeControlMode(CANTalon.TalonControlMode.Follower);
        lift4.changeControlMode(CANTalon.TalonControlMode.Follower);
        lift1.set(lift0.getDeviceID());
        lift2.set(lift0.getDeviceID());
        lift3.set(lift0.getDeviceID());
        lift4.set(lift0.getDeviceID());
    }
        //insert FRC code here
    public void setLift(double power) {
        lift0.set(power);
    }

    public void changeToPercentMode() {
        lift0.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }

    public void changeToVelocityMode() {
        lift0.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }

}
