package src.org.usfirst.frc.team3309.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterBack;
import org.usfirst.frc.team3309.robot.Constants;

public class Shooter extends Subsystem {

    private Solenoid actuator = new Solenoid(Constants.SHOOTER_SHIFTER);

    public Shooter() {
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ShooterBack());
    }

    public void actuateForward() {
        actuator.set(true);
    }

    public void actuateBack() {
        actuator.set(false);
    }

}
