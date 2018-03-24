package org.usfirst.frc.team3309.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltBarManualTest;
import org.usfirst.frc.team3309.lib.actuators.TalonSRXMC;
import org.usfirst.frc.team3309.robot.Constants;
import org.usfirst.frc.team3309.robot.Robot;

public class BeltBar extends Subsystem {

    private TalonSRXMC masterBar = new TalonSRXMC(Constants.BELTBAR_0);

    private AnalogInput hasCubeSensorLeft = new AnalogInput(Constants.BELTBAR_SHARP_SENSOR_LEFT);
    private AnalogInput hasCubeSensorRight = new AnalogInput(Constants.BELTBAR_SHARP_SENSOR_RIGHT);

    private DigitalInput hallEffectSensor = new DigitalInput(Constants.BELTBAR_HALL_EFFECT);

    private boolean inRecovery = false;

    private boolean isClimbing = false;

    private double goalAngle;

    // TODO determine top value
    private final int TOP_VALUE = 0;

    private final int MAX_CURRENT = 18;
    private final int MAX_CURRENT_DURATION = 125;

    public int FORWARD_SOFT_LIM = -400;
    public int REVERSE_SOFT_LIM = -2050;

    private final double LIM_TOLERANCE = 400;

    public BeltBar() {
        init();
    }

    public void init() {
        masterBar.setInverted(false);
        masterBar.setSensorPhase(true);

        masterBar.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
        masterBar.changeToPositionMode();

        masterBar.config_kP(0, 3, 10);
        masterBar.config_kI(0, 0, 10);
        masterBar.config_kD(0, 0.5, 10);
        masterBar.config_kF(0, 0.04, 10);
        masterBar.config_IntegralZone(0, 0, 0);
        masterBar.clearStickyFaults(10);

        if (Constants.currentRobot == Constants.Robot.PRACTICE) {
            REVERSE_SOFT_LIM = -2840;
            FORWARD_SOFT_LIM = -1200;
        }

        if (Constants.currentRobot == Constants.Robot.COMPETITION) {
            REVERSE_SOFT_LIM = -2100;
            FORWARD_SOFT_LIM = -500;
        }

        masterBar.configForwardSoftLimitThreshold(FORWARD_SOFT_LIM, 10);
        masterBar.configForwardSoftLimitEnable(true, 10);
        masterBar.configReverseSoftLimitThreshold(REVERSE_SOFT_LIM, 10);
        masterBar.configReverseSoftLimitEnable(true, 10);

        masterBar.configPeakCurrentLimit(MAX_CURRENT, 10);
        masterBar.configPeakCurrentDuration(MAX_CURRENT_DURATION, 10);
        masterBar.configContinuousCurrentLimit(1, 10);
        masterBar.enableCurrentLimit(true);

        changeToBrakeMode();
    }

    @Override
    public void periodic() {
        adjustBackInLimits();
        if (isClimbing) {
            DriverStation.reportWarning("I am climbing!", false);
            masterBar.configForwardSoftLimitEnable(false, 10);
            masterBar.configReverseSoftLimitEnable(false, 10);
            masterBar.changeToDisabledMode();
        }
        SmartDashboard.putNumber("Beltbar pos: ", getPosition());

    }


    private void adjustBackInLimits() {
        if (Robot.beltBar.getPosition() > Robot.beltBar.FORWARD_SOFT_LIM + LIM_TOLERANCE &&
                Robot.beltBar.getPosition() < Robot.beltBar.REVERSE_SOFT_LIM - LIM_TOLERANCE) {
            masterBar.set(ControlMode.Disabled, 0);
//            masterBar.reset();
//            init();
            DriverStation.reportError("Catting on beltbar!!!", false);
        } else {
            if (!isClimbing) {
                if (getPosition() > FORWARD_SOFT_LIM) {
                    masterBar.configForwardSoftLimitEnable(false, 10);
                    masterBar.set(ControlMode.Position, AssemblyLocation.INTAKE.getBeltBarPosition());
                    DriverStation.reportWarning("Beltbar exceeded forward limit! Correcting...", false);
                    inRecovery = true;
                } else if (getPosition() < REVERSE_SOFT_LIM) {
                    masterBar.configReverseSoftLimitEnable(false, 10);
                    masterBar.set(ControlMode.Position, AssemblyLocation.BOTTOM.getBeltBarPosition());
                    DriverStation.reportWarning("Beltbar exceeded reverse limit! Correcting...", false);
                    inRecovery = true;
                } else {
                    if (inRecovery) {
                        masterBar.configForwardSoftLimitEnable(true, 10);
                        masterBar.configReverseSoftLimitEnable(true, 10);
                        inRecovery = false;
                    }
                }
            }
        }

    }

    @Override
    protected void initDefaultCommand() {
        //     setDefaultCommand(new BeltBarManualTest());
    }

    public void sendToDashboard() {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("Beltbar");
        table.getEntry("Beltbar pos: ").setNumber(getPosition());
        table.getEntry("Beltbar percent output: ").setNumber(masterBar.getMotorOutputPercent());
        table.getEntry("Beltbar control mode: ").setString(masterBar.getControlMode().toString());
        table.getEntry("Beltbar goal: ").setNumber(goalAngle);
        table.getEntry("sees cube").setBoolean(isCubePresent());
        table.getEntry("sharp sensor left").setNumber(hasCubeSensorLeft.getAverageVoltage());
        table.getEntry("sharp sensor right").setNumber(hasCubeSensorRight.getAverageVoltage());
        table.getEntry("sharp sensor average").setNumber(getSharpSensorValue());
        table.getEntry("current: ").setNumber(masterBar.getOutputCurrent());

    }

    public double getPosition() {
        return masterBar.getSelectedSensorPosition(0);
    }

    public void set(double value) {
        masterBar.set(value);
    }

    public void changeToBrakeMode() {
        masterBar.setNeutralMode(NeutralMode.Brake);
    }

    public void changeToCoastMode() {
        masterBar.setNeutralMode(NeutralMode.Coast);
    }

    public void changeToMotionMagicMode() {
        masterBar.changeToMotionMagic();
    }

    public void configCruiseVelocity(int sensorUnitsPer100ms) {
        masterBar.configMotionCruiseVelocity(sensorUnitsPer100ms, 10);
    }

    public void changeToPositionMode() {
        masterBar.changeToPositionMode();
    }

    public void changeToPercentMode() {
        masterBar.changeToPercentMode();
    }

    public double getGoalAngle() {
        return goalAngle;
    }

    public void setGoalAngle(double goalAngle) {
        this.goalAngle = goalAngle;
    }

    public boolean isCubePresent() {
        return getSharpSensorValue() > 1250;
    }

    public double getSharpSensorValue() {
        return (hasCubeSensorLeft.getAverageValue() + hasCubeSensorRight.getAverageValue()) / 2.0;
    }

    public boolean isAtTop() {
        return hallEffectSensor.get();
    }

    public void resetToTop() {
        masterBar.setSelectedSensorPosition(TOP_VALUE, 0, 10);
    }

    public double getCurrent() {
        return masterBar.getOutputCurrent();
    }

    public void setClimbing(boolean climbing) {
        isClimbing = climbing;
    }

}
