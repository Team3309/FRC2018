package org.usfirst.frc.team3309.robot;

import org.usfirst.team3309.subsystems.Drive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Sensors {
	
	private static ADXRS450_Gyro gyro;
	
	static {
		gyro = new ADXRS450_Gyro();
	}
	
	public static double getAngle() {
		return gyro.getAngle();
	}
	
	public static double getPos() {
		return Drive.getInstance().getDistanceTraveled();
	}
	
	public static void resetDrive() {
		Drive.getInstance().resetDrive();
	}

}
