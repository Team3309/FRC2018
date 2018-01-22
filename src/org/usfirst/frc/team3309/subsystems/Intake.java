package org.usfirst.frc.team3309.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.commands.subsystems.intake.IntakeTeleop;
import org.usfirst.frc.team3309.lib.actuators.VictorSPXMC;
import org.usfirst.frc.team3309.robot.Constants;

public class Intake extends Subsystem {

    private VictorSPXMC leftArm = new VictorSPXMC(Constants.INTAKE_LEFT_ROLLER);
    private VictorSPXMC rightArm = new VictorSPXMC(Constants.INTAKE_RIGHT_ROLLER);

    private DoubleSolenoid hardStopActuator = new DoubleSolenoid(Constants.INTAKE_HARDSTOP_ACTUATOR_A,
            Constants.INTAKE_HARDSTOP_ACTUATOR_B);

    private DoubleSolenoid innerActuator = new DoubleSolenoid(Constants.INTAKE_INNER_ACTUATOR_A,
            Constants.INTAKE_INNER_ACTUATOR_B);

    public Intake() {
        leftArm.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        rightArm.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        leftArm.enableBrakeMode(false);
        rightArm.enableBrakeMode(false);
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

    public void setLeftRightRoller(double left, double right) {
        setLeftRoller(left);
        setRightRoller(right);
    }

    public void setLeftRoller(double power) {
        leftArm.set(power);
    }

    public void setRightRoller(double power) {
        rightArm.set(power);
    }


}
