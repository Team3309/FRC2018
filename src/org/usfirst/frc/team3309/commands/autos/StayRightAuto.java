package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraight;
import org.usfirst.frc.team3309.robot.Robot;

public class StayRightAuto extends CommandGroup {

    @Override
    public synchronized void start() {
        if (Robot.isRightScale()) {
            addSequential(new ScaleOnlyAuto(true, false));
        } else if (Robot.isRightSwitch()) {
            addSequential(new SideSwitchAuto(true));
        } else {
            addSequential(new DriveStraight(95, 25000, 0));
        }
        super.start();
    }

}
