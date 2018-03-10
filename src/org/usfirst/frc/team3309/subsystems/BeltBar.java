package org.usfirst.frc.team3309.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltBarManualTest;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltBarMoveToPos;
import org.usfirst.frc.team3309.lib.actuators.TalonSRXMC;
import org.usfirst.frc.team3309.robot.Constants;

public class BeltBar extends Subsystem {

    private TalonSRXMC masterBar = new TalonSRXMC(Constants.BELTBAR_0);

    private AnalogInput hasCubeSensorLeft = new AnalogInput(Constants.BELTBAR_SHARP_SENSOR_LEFT);
    private AnalogInput hasCubeSensorRight = new AnalogInput(Constants.BELTBAR_SHARP_SENSOR_RIGHT);

    private DigitalInput hallEffectSensor = new DigitalInput(Constants.BELTBAR_HALL_EFFECT);

    private double goalAngle;

    // TODO determine top value
    private final int TOP_VALUE = 0;

    private final int MAX_CURRENT = 18;
    private final int MAX_CURRENT_DURATION = 125;

    public BeltBar() {
        masterBar.setInverted(false);
        masterBar.setSensorPhase(true);
        masterBar.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
        masterBar.changeToPositionMode();
        masterBar.config_kP(0, 0.98, 0);
        masterBar.config_kF(0, 0.04, 0);

        /* practice bot
        masterBar.configForwardSoftLimitThreshold(-1650, 0);
        masterBar.configForwardSoftLimitEnable(false, 0);
        masterBar.configReverseSoftLimitThreshold(-3100,0);
        masterBar.configReverseSoftLimitEnable(false, 0);*/

        masterBar.configForwardSoftLimitThreshold(-730, 0); // -400
        masterBar.configForwardSoftLimitEnable(true,0);
        masterBar.configReverseSoftLimitThreshold(-1990,0); // -2050
        masterBar.configReverseSoftLimitEnable(true, 0);

        masterBar.configPeakCurrentLimit(MAX_CURRENT, 0);
        masterBar.configPeakCurrentDuration(MAX_CURRENT_DURATION, 0);
        masterBar.configContinuousCurrentLimit(1, 0);
        masterBar.enableCurrentLimit(true);

        changeToBrakeMode();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new BeltBarManualTest());
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
     //   System.out.println(masterBar.);
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

    public boolean isAtTop(){ return hallEffectSensor.get(); }

    public void resetToTop() {
        masterBar.setSelectedSensorPosition(TOP_VALUE, 0,0);
    }

    public double getCurrent() {
        return masterBar.getOutputCurrent();
    }

}
