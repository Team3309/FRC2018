package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraight;
import org.usfirst.frc.team3309.robot.Robot;

public class StayRightAuto extends CommandGroup {

    private boolean shouldSwitchCube;

    public StayRightAuto(boolean shouldSwitchCube) {
        this.shouldSwitchCube = shouldSwitchCube;
    }

    @Override
    public synchronized void start() {
        if (Robot.isRightScale()) {
            addSequential(new ScaleOnlyAuto(true, shouldSwitchCube));
        } else if (Robot.isRightSwitch()) {
            addSequential(new SideSwitchAuto(true));
        } else {
            addSequential(new DriveStraight(105, 25000, 0));
        }
        super.start();
    }

}
