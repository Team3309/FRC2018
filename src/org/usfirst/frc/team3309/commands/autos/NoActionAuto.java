package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.robot.Robot;

public class NoActionAuto extends CommandGroup {

	public NoActionAuto() {
	    Robot.logger.info("Started: " + this.getName());
		requires(Robot.drive);
	}

}
