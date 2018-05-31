package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraight;
import org.usfirst.frc.team3309.robot.Robot;

public class StayRightAuto extends CommandGroup {

    @Override
    public synchronized void start() {
        if (Robot.isRightScale()) {
            new ScaleOnlyAuto(true, false).start();
        } else if (Robot.isRightSwitch()) {
            new SideSwitchAuto(true).start();
        } else {
            new AutoLineAuto().start();
        }
    }

}
