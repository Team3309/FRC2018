package org.usfirst.frc.team3309.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.commands.subsystems.intake.IntakeTeleop;
import org.usfirst.frc.team3309.lib.actuators.VictorSPXMC;
import org.usfirst.frc.team3309.robot.Constants;

public class Intake extends Subsystem {

    private VictorSPXMC leftMotor = new VictorSPXMC(Constants.INTAKE_LEFT_ROLLER);
    private VictorSPXMC rightMotor = new VictorSPXMC(Constants.INTAKE_RIGHT_ROLLER);

    private DoubleSolenoid hardStopActuator = new DoubleSolenoid(Constants.INTAKE_HARDSTOP_ACTUATOR_A,
            Constants.INTAKE_HARDSTOP_ACTUATOR_B);

    private DoubleSolenoid innerActuator = new DoubleSolenoid(Constants.INTAKE_INNER_ACTUATOR_A,
            Constants.INTAKE_INNER_ACTUATOR_B);

    public Intake() {
        leftMotor.set(0);
        rightMotor.set(0);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new IntakeTeleop());
    }

    public void setInnerActuator(DoubleSolenoid.Value value) {
        innerActuator.set(value);
    }

    public void setHardStopActuator(DoubleSolenoid.Value value) {
        hardStopActuator.set(value);
    }

    public void setLeftRight(double left, double right) {
        setLeft(left);
        setRight(right);
    }

    public void setLeft(double power) {
        leftMotor.set(ControlMode.PercentOutput, power);
    }

    public void setRight(double power) {
        rightMotor.set(ControlMode.PercentOutput, power);
    }

    public void changeToPercentMode() {
        leftMotor.changeToPercentMode();
        rightMotor.changeToPercentMode();
    }

}
