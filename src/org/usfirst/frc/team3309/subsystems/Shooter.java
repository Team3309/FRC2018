package org.usfirst.frc.team3309.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.lib.actuators.VictorSPXMC;
import org.usfirst.frc.team3309.robot.Constants;

public class Shooter extends Subsystem {

    private VictorSPXMC shooterMotor = new VictorSPXMC(Constants.SHOOTER_0);

    public Shooter() {
        shooterMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void set(double power) {
        shooterMotor.set(power);
    }

}
