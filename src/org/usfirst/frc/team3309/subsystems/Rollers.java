package org.usfirst.frc.team3309.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.commands.subsystems.rollers.RollersTeleop;
import org.usfirst.frc.team3309.lib.actuators.VictorSPXMC;
import org.usfirst.frc.team3309.robot.Constants;
import org.usfirst.frc.team3309.robot.Robot;

import java.sql.Driver;

public class Rollers extends Subsystem {

    private VictorSPXMC leftMotor = new VictorSPXMC(Constants.ROLLER_LEFT);
    private VictorSPXMC rightMotor = new VictorSPXMC(Constants.ROLLER_RIGHT);

    private boolean enableRollerPower = false;
    private double defaultAutoPower = Constants.AUTO_ROLLER_INTAKE_POWER;

    public Rollers() {
        leftMotor.set(0);
        rightMotor.set(0);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RollersTeleop());
    }

    @Override
    public void periodic() {
        if (DriverStation.getInstance().isAutonomous() && enableRollerPower) {
            Robot.rollers.setLeftRight(-defaultAutoPower, defaultAutoPower);
        } else if (DriverStation.getInstance().isAutonomous()) {
            Robot.rollers.setLeftRight(0, 0);
        }
    }

    public void setLeftRight(double left, double right) {
        setLeft(-left);
        setRight(-right);
    }

    public void setLeft(double power) {
        leftMotor.set(ControlMode.PercentOutput, power);
    }

    public void setRight(double power) {
        rightMotor.set(ControlMode.PercentOutput, power);
    }

    public void setEnableRollerPower(boolean enableRollerPower) {
        this.enableRollerPower = enableRollerPower;
    }

}
