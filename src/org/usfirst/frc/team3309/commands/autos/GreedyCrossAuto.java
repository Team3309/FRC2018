package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraightVel;

public class GreedyCrossAuto extends CommandGroup {
    private boolean isLeft;

    public GreedyCrossAuto(boolean isLeft) {
        this.isLeft = isLeft;
    }

    @Override
    public void start() {
        addSequential(new DriveStraightVel(-50, 20000, 10));
     /*   if(isLeft == Robot.isLeftSwitch())
        {
            addSequential(new DriveArc(Length.fromInches(33.09), Robot.isLeftSwitch() ? 90 : -90,20000,false));
            addSequential(new DriveStraightVel(-1,10000,2));
            addSequential(new ShooterShoot());
        }
        else
        {
            addSequential(new DriveStraightVel(-3,20000,2));
        }*/
        super.start();
    }
}
