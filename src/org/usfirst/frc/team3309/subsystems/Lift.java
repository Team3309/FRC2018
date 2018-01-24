package org.usfirst.frc.team3309.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.lib.sensors.LimitSwitch;
import org.usfirst.frc.team3309.lib.actuators.TalonSRXMC;
import org.usfirst.frc.team3309.lib.actuators.VictorSPXMC;
import org.usfirst.frc.team3309.robot.Constants;

public class Lift extends Subsystem {

    private TalonSRXMC lift0 = new TalonSRXMC(Constants.LIFT_0);
    private VictorSPXMC lift1 = new VictorSPXMC(Constants.LIFT_1);
    private VictorSPXMC lift2 = new VictorSPXMC(Constants.LIFT_2);
    private VictorSPXMC lift3 = new VictorSPXMC(Constants.LIFT_3);
    private VictorSPXMC lift4 = new VictorSPXMC(Constants.LIFT_4);

    private LimitSwitch topLimitSwitch = new LimitSwitch(Constants.LIFT_TOP_LIMIT_SWITCH);
    private LimitSwitch bottomLimitSwitch = new LimitSwitch(Constants.LIFT_BOTTOM_LIMIT_SWITCH);

    private DoubleSolenoid liftShifter = new DoubleSolenoid(Constants.LIFT_SHIFTER_A,
            Constants.LIFT_SHIFTER_B);

    private double goalPos;

    public Lift() {
        topLimitSwitch.reset();
        bottomLimitSwitch.reset();
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

    /*
    * TODO: implement conversion to inches HEIGHT
    * */
    public double inchesToEncoderCounts(double encoderCounts) {
        return encoderCounts * 0.0;
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

    public boolean isTopLimitSwitch() {
        return topLimitSwitch.isSwitchSet();
    }

    public boolean isBottomLimitSwitch() {
        return bottomLimitSwitch.isSwitchSet();
    }

}
