package src.org.usfirst.frc.team3309.commands.subsystems.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterBack;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterForward;

public class ShooterShoot extends CommandGroup {

    public ShooterShoot() {
        addSequential(new ShooterForward());
        addSequential(new ShooterBack());
    }

}
