package org.usfirst.frc.team3309.subsystems;

import com.ctre.CANTalon;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftCheckLimits;
import org.usfirst.frc.team3309.lib.sensors.LimitSwitch;
import org.usfirst.frc.team3309.lib.actuators.TalonSRXMC;
import org.usfirst.frc.team3309.lib.actuators.VictorSPXMC;
import org.usfirst.frc.team3309.robot.Constants;

public class Lift extends Subsystem {

    private TalonSRXMC lift0 = new TalonSRXMC(Constants.LIFT_0);
    private TalonSRXMC lift1 = new TalonSRXMC(Constants.LIFT_1);
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
        setDefaultCommand(new LiftCheckLimits());
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

    public void resetToBottom() {
        lift0.setPosition(0);
        lift0.setAnalogPosition(0);
    }

    public void resetToTop() {
        lift0.setPosition(Constants.LIFT_MAX_ENC_POSITION);
        lift0.setAnalogPosition((int)Constants.LIFT_MAX_ENC_POSITION);
    }

    public void enableBrakeMode(boolean on){
        lift0.enableBrakeMode(on);
        lift1.enableBrakeMode(on);
        lift2.enableBrakeMode(on);
        lift3.enableBrakeMode(on);
        lift4.enableBrakeMode(on);
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
