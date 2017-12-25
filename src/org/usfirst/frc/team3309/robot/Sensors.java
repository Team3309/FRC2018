package org.usfirst.frc.team3309.robot;

import org.usfirst.team3309.subsystems.Drive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Sensors {
	
	private static ADXRS450_Gyro gyro;
	
	static {
		gyro = new ADXRS450_Gyro();
	}
	
	public static double getAngle() {
		return gyro.getAngle(); // in continuous degrees with wraparound from 360 to 361
	}
	
	public static double getPos() { // encoder counts
		return Drive.getInstance().getDistanceTraveled();
	}
	
	public static double getVel() { // encoder counts per ms
		return Drive.getInstance().getAverageVelocity();
	}
	
	public static void resetDrive() {
		Drive.getInstance().resetDrive();
		gyro.reset();
	}
	

}
