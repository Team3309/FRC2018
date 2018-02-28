package org.usfirst.frc.team3309.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltBarManualTest;
import org.usfirst.frc.team3309.lib.actuators.TalonSRXMC;
import org.usfirst.frc.team3309.robot.Constants;

public class BeltBar extends Subsystem {

    private TalonSRXMC masterBar = new TalonSRXMC(Constants.BELTBAR_0);

    private double goalAngle;

    public BeltBar() {
        masterBar.set(0);
        masterBar.configPeakOutputForward(1.0, 0);
        masterBar.configPeakOutputReverse(-1.0, 0);
        masterBar.setInverted(true);
        masterBar.setSensorPhase(false);
        masterBar.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
        masterBar.changeToPositionMode();

/*        masterBar.config_kP(0, 0.1, 0);
        masterBar.config_kF(0, 0.05, 0);*/
        // TODO fix soft limits
/*        masterBar.configForwardSoftLimitThreshold(140, 0);
        masterBar.configForwardSoftLimitEnable(true, 0);
        masterBar.configReverseSoftLimitThreshold(-1500,0);
        masterBar.configReverseSoftLimitEnable(true, 0);*/
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
    }

    public double getPosition() {
        return masterBar.getSensorCollection().getPulseWidthPosition();
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
}
