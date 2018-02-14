package org.usfirst.frc.team3309.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftManualTest;
import org.usfirst.frc.team3309.lib.actuators.TalonSRXMC;
import org.usfirst.frc.team3309.lib.actuators.VictorSPXMC;
import org.usfirst.frc.team3309.lib.sensors.LimitSwitch;
import org.usfirst.frc.team3309.robot.Constants;

public class Lift extends Subsystem {

    private TalonSRXMC lift0 = new TalonSRXMC(Constants.LIFT_0);
    private TalonSRXMC lift1 = new TalonSRXMC(Constants.LIFT_1);
    private VictorSPXMC lift2 = new VictorSPXMC(Constants.LIFT_2);
    private VictorSPXMC lift3 = new VictorSPXMC(Constants.LIFT_3);
    private VictorSPXMC lift4 = new VictorSPXMC(Constants.LIFT_4);

    private LimitSwitch topLimitSwitch = new LimitSwitch(Constants.LIFT_TOP_LIMIT_SWITCH);
    private LimitSwitch bottomLimitSwitch = new LimitSwitch(Constants.LIFT_BOTTOM_LIMIT_SWITCH);

    private Solenoid liftShifter = new Solenoid(Constants.LIFT_SHIFTER);

    private double goalPos;

    public Lift() {
        topLimitSwitch.reset();
        bottomLimitSwitch.reset();
        lift0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0,
                0);
        lift0.set(ControlMode.Position, 0.0);
        lift1.follow(lift0);
        lift2.follow(lift0);
        lift3.follow(lift0);
        lift4.follow(lift0);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new LiftManualTest());
     //   setDefaultCommand(new LiftSet(0));
    }

    /*
     * TODO: implement conversion to inches HEIGHT
     * */
    public double inchesToEncoderCounts(double encoderCounts) {
        return encoderCounts * 0.0;
    }

    public void resetToBottom() {
        lift0.getSensorCollection().setAnalogPosition(0, 0);
    }

    public void resetToTop() {
        lift0.getSensorCollection().setAnalogPosition((int)Constants.LIFT_MAX_ENC_POSITION, 0);
    }

    public void changeToBrakeMode(){
        lift0.setNeutralMode(NeutralMode.Brake);
    }

    public void changeToCoastMode() {
        lift0.setNeutralMode(NeutralMode.Coast);
    }

    public void set(double value) {
        lift0.set(value);
    }

    public void setVelocity(double velocity) {
        lift0.set(ControlMode.Velocity, velocity);
    }

    public void changeToPositionMode() {
        lift0.changeToPercentMode();
    }

    public void changeToPercentMode() {
        lift0.changeToPercentMode();
    }

    public void changeToVeloctiyMode() {
        lift0.changeToVelocityMode();
    }

    public void setLiftShifter(boolean value) {
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
