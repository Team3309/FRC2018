package org.usfirst.frc.team3309.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftManualTest;
import org.usfirst.frc.team3309.lib.actuators.TalonSRXMC;
import org.usfirst.frc.team3309.lib.actuators.VictorSPXMC;
import org.usfirst.frc.team3309.robot.Constants;

public class Lift extends Subsystem {

    private TalonSRXMC lift0 = new TalonSRXMC(Constants.LIFT_0);
    private TalonSRXMC lift1 = new TalonSRXMC(Constants.LIFT_1);
    private VictorSPXMC lift2 = new VictorSPXMC(Constants.LIFT_2);
    private VictorSPXMC lift3 = new VictorSPXMC(Constants.LIFT_3);
    private VictorSPXMC lift4 = new VictorSPXMC(Constants.LIFT_4);

    private DoubleSolenoid secondStageHolder = new DoubleSolenoid(Constants.LIFT_HOLDER_A, Constants.LIFT_HOLDER_B);

    private DigitalInput bannerSensor = new DigitalInput(Constants.LIFT_BANNER_SENSOR);

    private Solenoid liftShifter = new Solenoid(Constants.LIFT_SHIFTER);

    private double liftPos;
    private double beltbarPos;

    private double goalPos;

    public final int FORWARD_LIM = 47000;

    public Lift() {
        liftPos = AssemblyLocation.BOTTOM.getElevatorPosition();
        beltbarPos = AssemblyLocation.BOTTOM.getBeltBarPosition();
        lift0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0,
                10);

        lift0.configForwardSoftLimitThreshold(FORWARD_LIM, 10);
        lift0.configForwardSoftLimitEnable(true, 10);

        lift0.config_kP(0, 0.26, 10);
        lift0.config_kI(0,3.2*Math.pow(10,-5),10);
        lift0.config_kD(0, 30, 10);
        lift0.config_IntegralZone(0,200,10);
        lift0.config_kF(0, 0.024, 10);

        lift0.configClosedloopRamp(0.22, 10);

        lift0.configPeakOutputForward(1.0, 10);
        lift0.configPeakOutputReverse(-0.4, 10);

        lift0.changeToPositionMode();

        if(Constants.currentRobot == Constants.Robot.PRACTICE) {
            lift0.setSensorPhase(true);
            lift0.setInverted(false);
        }

        if(Constants.currentRobot == Constants.Robot.COMPETITION) {
            lift0.setInverted(false);
            lift0.setSensorPhase(true);
        }

        lift1.follow(lift0);
        lift2.follow(lift0);
        lift3.follow(lift0);
        lift4.follow(lift0);

        setHolderIn();
        changeToBrakeMode();
    }

    @Override
    protected void initDefaultCommand() {
     //   setDefaultCommand(new LiftManualTest());
    }

    @Override
    public void periodic() {
        if (getPosition() > FORWARD_LIM) {
            lift0.configForwardSoftLimitEnable(false, 10);
            lift0.set(ControlMode.Position, AssemblyLocation.SCALE_UP.getElevatorPosition());
            DriverStation.reportWarning("Lift exceeded forward limit! Correcting...", false);
        } else {
            lift0.configForwardSoftLimitEnable(true, 10);
            lift0.configReverseSoftLimitEnable(true, 10);
        }
    }

    public void sendToDashboard() {
        SmartDashboard.putData(this);
        NetworkTable table = NetworkTableInstance.getDefault().getTable("Lift");
        table.getEntry("lift pos: ").setNumber(getPosition());
        table.getEntry("lift goal pos: ").setNumber(getGoalPos());
        table.getEntry("lift control mode: ").setString(lift0.getControlMode().toString());
        table.getEntry("lift percent mode: ").setNumber(lift0.getMotorOutputPercent());
        table.getEntry("lift percent output: ").setNumber(lift0.getMotorOutputPercent());
        table.getEntry("lift banner sensor: ").setBoolean(bannerSensor.get());
        table.getEntry("banner sensor trigger: ").setBoolean(bannerSensor.isAnalogTrigger());
    }

    public void reset() {
        resetToBottom();
    }

    public void resetToBottom() {
        lift0.setSelectedSensorPosition(0, 0, 0);
    }

    public double getPosition() {
        return lift0.getSelectedSensorPosition(0);
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

    public void changeToMotionMagicMode() {
        lift0.changeToMotionMagic();
    }

    public void setCruiseVelocity(int vel)
    {
        lift0.configMotionCruiseVelocity(vel,0);
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

    public boolean isAtBottom() {
        return bannerSensor.get();
    }

    public double getError() {
        return lift0.getClosedLoopError(0);
    }

    public void setHolderIn() {
        secondStageHolder.set(DoubleSolenoid.Value.kForward);
    }

    public void setHolderOut() {
        secondStageHolder.set(DoubleSolenoid.Value.kReverse);
    }

    public double getFORWARD_LIM() {
        return FORWARD_LIM;
    }

    public TalonSRX getLeft1() {
        return lift1.getTalonSRX();
    }

}
