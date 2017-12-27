package org.usfirst.frc.team3309.robot;

import library.communications.BlackBox;

import org.usfirst.frc.team3309.auto.AutoModeExecuter;
import org.usfirst.frc.team3309.auto.AutoRoutine;
import org.usfirst.team3309.subsystems.Drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * @author Chase.Blagden
 */
public class Robot extends IterativeRobot {

	private AutoRoutine autoThread;
	
	@Override
	public void robotInit() {
		Systems.init();
		BlackBox.initLog("3309" + String.valueOf(DriverStation.getInstance().getAlliance()), "X position", "Y position");
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
		Sensors.updateDrive();
		Systems.updateAuto();
		Systems.sendToDashboard();
		BlackBox.writeLog(String.valueOf(Drive.getInstance().getInches(Sensors.getXPos())), 
				String.valueOf(Drive.getInstance().getInches(Sensors.getYPos())));
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
