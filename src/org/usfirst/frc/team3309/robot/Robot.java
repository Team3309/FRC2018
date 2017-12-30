package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.subsystems.Drive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * @author Chase.Blagden
 */
public class Robot extends IterativeRobot {
	
	public static Drive drive = new Drive();
	private Command autoCommand;
	public OI oi;

	@Override
	public void robotInit() {
		oi = new OI();
		AutoModeExecutor.displayAutos();
	}

	@Override
	public void autonomousInit() {
		autoCommand = AutoModeExecutor.getAutoSelected();
		if (autoCommand != null) {
			autoCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autoCommand != null) {
			autoCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
}
