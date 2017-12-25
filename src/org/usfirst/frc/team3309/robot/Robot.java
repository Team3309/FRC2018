package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.auto.AutoModeExecuter;
import org.usfirst.frc.team3309.auto.AutoRoutine;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * @author Chase.Blagden
 * 
 */
public class Robot extends IterativeRobot {

	private AutoRoutine autoThread;

	@Override
	public void robotInit() {
		Systems.init();
		AutoModeExecuter.displayAutos();
	}

	@Override
	public void autonomousInit() {
		autoThread = AutoModeExecuter.getAutoSelected();
		if (autoThread != null) {
			autoThread.startAutoThread();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Systems.updateAuto();
		Systems.sendToDashboard();
	}

	@Override
	public void teleopInit() {
		if (autoThread != null) {
			autoThread.stopAutoThread();
		}
		Systems.initTeleop();
	}

	@Override
	public void teleopPeriodic() {
	}

	@Override
	public void testPeriodic() {
	}
}
