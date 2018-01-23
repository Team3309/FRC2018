package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.robot.Robot;

public class NoActionsAuto extends CommandGroup {
	
	public NoActionsAuto() {
	    Robot.logger.info("Started: " + this.getName());
		requires(Robot.drive);
	}

}
