package org.usfirst.frc.team3309.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltBarManualTest;
import org.usfirst.frc.team3309.lib.actuators.TalonSRXMC;
import org.usfirst.frc.team3309.robot.Constants;

public class BeltBar extends Subsystem {

    private TalonSRXMC masterBar = new TalonSRXMC(Constants.BELTBAR_0);

    private double goalAngle;

    public BeltBar() {
        masterBar.set(0);
        masterBar.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new BeltBarManualTest());
    }

    public double getBarAngle() {
        return masterBar.getSensorCollection().getAnalogIn();
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
