package org.usfirst.frc.team3309.robot;

import library.controllers.drive.Waypoint;

import org.usfirst.team3309.subsystems.Drive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Sensors {

	private static ADXRS450_Gyro gyro;
	private static double curX = 0.0;
	private static double curY = 0.0;

	static {
		gyro = new ADXRS450_Gyro();
	}

	public static double getAngle() {
		return gyro.getAngle(); // in continuous degrees with wraparound from
								// 360 to 361
	}

	public static double getPos() { // encoder counts
		return Drive.getInstance().getDistanceTraveled();
	}

	public static double getVel() { // encoder counts per ms
		return Drive.getInstance().getAverageVelocity();
	}

	public static double getXPos() {
		return curX;
	}

	public static double getYPos() {
		return curY;
	}
	
	public static Waypoint getPoint() {
		return new Waypoint(curX, curY);
	}

	public static void updateDrive() {
		curX += getVel() * Math.cos(getAngle() + 90);
		curY += getVel() * Math.sin(getAngle() + 90);
	}

	public static void resetDrive() {
		Drive.getInstance().resetDrive();
		gyro.reset();
	}

}
