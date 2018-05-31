package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsClamp;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsOpen;
import org.usfirst.frc.team3309.commands.subsystems.rollers.RollersActuate;
import org.usfirst.frc.team3309.lib.WaitCommand;
import org.usfirst.frc.team3309.robot.Constants;
import org.usfirst.frc.team3309.robot.Robot;

public class FindAndGetCube extends CommandGroup {

    public FindAndGetCube() {
        addSequential(new MoveAssembly(AssemblyLocation.INTAKE));
 /*       addSequential(new WaitForCube());
        addParallel(new Command() {
            @Override
            public void execute() {
                DriverStation.reportWarning("I got a cube all by myself!!!", false);
            }

            @Override
            protected boolean isFinished() {
                return false;
            }
        });
        addSequential(new ArmsClamp());
        Robot.beltBar.resetTimer();*/
    }

}
