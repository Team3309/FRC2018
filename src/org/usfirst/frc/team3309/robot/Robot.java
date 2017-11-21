package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	@Override
	public void robotInit() {
		Systems.init();
	}

	@Override
	public void autonomousInit() {
		
	}

	@Override
	public void autonomousPeriodic() {
	}
	
	@Override
	public void teleopInit() {
		Systems.initTeleop();
	}

	@Override
	public void teleopPeriodic() {
	}

	@Override
	public void testPeriodic() {
	}
}

