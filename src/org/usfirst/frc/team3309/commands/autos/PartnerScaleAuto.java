package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.MoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraight;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTurn;
import org.usfirst.frc.team3309.robot.Robot;

public class PartnerScaleAuto extends CommandGroup {

    @Override
    public synchronized void start() {
        if (Robot.isRightScale()) {
            addSequential(new DriveStraight(306, 30000, 0));
            addSequential(new DriveTurn(90, 1.0, true));
            addSequential(new DriveStraight(-6, 17000, 0));
            addSequential(new MoveAssembly(AssemblyLocation.SWITCH));
            super.start();
        } else if (Robot.isRightSwitch()) {
            new SideSwitchAuto(true).start();
        } else {
            new AutoLineAuto().start();
        }
    }

}
