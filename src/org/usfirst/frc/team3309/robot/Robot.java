package org.usfirst.frc.team3309.robot;

import lib.communications.BlackBox;

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
		BlackBox.initLog("PursuitTest", "X pos", "Y pos");
		System.out.println("Starting robot");
	}

	@Override
	public void autonomousInit() {
		System.out.println("Starting auto");
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
		System.out.println("Starting teleop");
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
