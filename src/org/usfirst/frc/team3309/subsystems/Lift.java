package org.usfirst.frc.team3309.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    private PigeonIMU pigeonIMU = new PigeonIMU(lift1);

    private LimitSwitch bottomLimitSwitch = new LimitSwitch(Constants.LIFT_BOTTOM_LIMIT_SWITCH);

    private Solenoid liftShifter = new Solenoid(Constants.LIFT_SHIFTER);

    private double goalPos;

    public Lift() {
        bottomLimitSwitch.reset();
        lift0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0,
                0);
        lift0.configPeakOutputForward(0.8, 0);
        lift0.configPeakOutputReverse(-0.8, 0);
        lift0.changeToPositionMode();
        lift0.setSensorPhase(false);
        lift1.follow(lift0);
        lift2.follow(lift0);
        lift3.follow(lift0);
        lift4.follow(lift0);
        changeToBrakeMode();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new LiftManualTest());
    }

    public void sendToDashboard() {
        SmartDashboard.putData(this);
        NetworkTable table = NetworkTableInstance.getDefault().getTable("Lift");
        table.getEntry("lift pos: ").setNumber(getPosition());
        table.getEntry("lift goal pos: ").setNumber(getGoalPos());
        table.getEntry("lift limit switch: ").setBoolean(isBottomLimitSwitch());
    }

    public void reset() {
        resetToBottom();
    }

    public void resetToBottom() {
        lift0.getSensorCollection().setQuadraturePosition(0, 0);
    }

    public double getPosition() {
        return lift0.getSensorCollection().getQuadraturePosition();
    }

    public void changeToBrakeMode() {
        lift0.setNeutralMode(NeutralMode.Brake);
    }

    public void changeToCoastMode() {
        lift0.setNeutralMode(NeutralMode.Coast);
    }

    public void set(double value) {
        lift0.set(value);
    }

    public void set(ControlMode controlMode, double value) {
        lift0.set(controlMode, value);
    }

    public void changeToPositionMode() {
        lift0.changeToPositionMode();
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

    public boolean isBottomLimitSwitch() {
        return bottomLimitSwitch.isSwitchSet();
    }

}
