package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraightVel;
import org.usfirst.frc.team3309.robot.Robot;

public class GreedyCrossAuto extends CommandGroup {
    private boolean isLeft;

    public GreedyCrossAuto(boolean isLeft) {
        this.isLeft = isLeft;
    }

    @Override
    public synchronized void start() {
        addSequential(new DriveStraightVel((140 + 56 / 2), 20000, 4));

        super.start();
    }
}
