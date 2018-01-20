package org.usfirst.frc.team3309.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.lib.actuators.TalonSRXMC;
import org.usfirst.frc.team3309.lib.actuators.VictorSPXMC;
import org.usfirst.frc.team3309.robot.RobotMap;

public class Lift extends Subsystem {

    private TalonSRXMC lift0 = new TalonSRXMC(RobotMap.LIFT_0);
    private VictorSPXMC lift1 = new VictorSPXMC(RobotMap.LIFT_1);
    private VictorSPXMC lift2 = new VictorSPXMC(RobotMap.LIFT_2);
    private VictorSPXMC lift3 = new VictorSPXMC(RobotMap.LIFT_3);
    private VictorSPXMC lift4 = new VictorSPXMC(RobotMap.LIFT_4);

    private DoubleSolenoid liftShifter = new DoubleSolenoid(RobotMap.LIFT_SHIFTER_A,
            RobotMap.LIFT_SHIFTER_B);

    private double goalPos;

    public Lift() {
        lift0.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
        lift0.changeControlMode(CANTalon.TalonControlMode.Position);
        lift1.changeControlMode(CANTalon.TalonControlMode.Follower);
        lift2.changeControlMode(CANTalon.TalonControlMode.Follower);
        lift3.changeControlMode(CANTalon.TalonControlMode.Follower);
        lift4.changeControlMode(CANTalon.TalonControlMode.Follower);
        lift1.set(lift0.getDeviceID());
        lift2.set(lift0.getDeviceID());
        lift3.set(lift0.getDeviceID());
        lift4.set(lift0.getDeviceID());
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void setLift(double power) {
        lift0.set(power);
    }

    public void changeToPercentMode() {
        lift0.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }

    public void changeToVelocityMode() {
        lift0.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }

    public void setLiftShifter(DoubleSolenoid.Value value) {
        liftShifter.set(value);
    }

    public double getGoalPos() {
        return goalPos;
    }

    public void setGoalPos(double goalPos) {
        this.goalPos = goalPos;
    }
}
