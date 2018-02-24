package src.org.usfirst.frc.team3309.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsCheckForCube;
import org.usfirst.frc.team3309.robot.Constants;

public class Arms extends Subsystem {

    private DoubleSolenoid leftActuator = new DoubleSolenoid(Constants.ARMS_LEFT_ACTUATOR_A,
            Constants.ARMS_LEFT_ACTUATOR_B);

    private DoubleSolenoid rightActuator = new DoubleSolenoid(Constants.ARMS_RIGHT_ACTUATOR_A,
            Constants.ARMS_RIGHT_ACTUATOR_B);

    private AnalogInput hasCubeSensor = new AnalogInput(Constants.ARMS_SHARP_SENSOR);

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ArmsCheckForCube());
    }

    private void setRightActuator(DoubleSolenoid.Value value) {
        rightActuator.set(value);
    }

    private void setLeftActuator(DoubleSolenoid.Value value) {
        leftActuator.set(value);
    }

    public boolean isCubeIn() {
        return hasCubeSensor.getAverageVoltage() > 0.7;
    }

    public void clampArms() {
        setLeftActuator(DoubleSolenoid.Value.kForward);
        setRightActuator(DoubleSolenoid.Value.kForward);
    }

    public void openArms() {
        setLeftActuator(DoubleSolenoid.Value.kReverse);
        setRightActuator(DoubleSolenoid.Value.kReverse);
    }

}
