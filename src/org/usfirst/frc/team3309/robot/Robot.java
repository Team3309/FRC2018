package org.usfirst.frc.team3309.robot;

import library.communications.BlackBox;

import org.usfirst.frc.team3309.subsystems.Drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * @author Chase.Blagden
 */
public class Robot extends IterativeRobot {
	
	public static final Drive drive = new Drive();
	private Command autoCommand;
	public OI oi;

	@Override
	public void robotInit() {
		oi = new OI();
		BlackBox.initLog("3309" + String.valueOf(DriverStation.getInstance().getAlliance()), "X position",
				"Y position");
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void disabledPeriodic() {
	}
}
