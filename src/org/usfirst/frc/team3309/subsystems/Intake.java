package org.usfirst.frc.team3309.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import lib.actuators.VictorSPXMC;
import org.usfirst.frc.team3309.commands.subsystems.intake.IntakeTeleop;
import org.usfirst.frc.team3309.robot.RobotMap;

public class Intake extends Subsystem {

    private VictorSPXMC leftArm = new VictorSPXMC(RobotMap.INTAKE_LEFT_ROLLER);
    private VictorSPXMC rightArm = new VictorSPXMC(RobotMap.INTAKE_RIGHT_ROLLER);

    private DoubleSolenoid hardStopActuator = new DoubleSolenoid(RobotMap.INTAKE_HARDSTOP_ACTUATOR_A,
            RobotMap.INTAKE_HARDSTOP_ACTUATOR_B);

    private DoubleSolenoid innerActuator = new DoubleSolenoid(RobotMap.INTAKE_INNER_ACTUATOR_A,
            RobotMap.INTAKE_INNER_ACTUATOR_B);

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
