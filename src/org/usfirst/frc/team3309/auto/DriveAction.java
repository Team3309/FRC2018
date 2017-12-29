package org.usfirst.frc.team3309.auto;

import library.controllers.Controller;

import org.usfirst.team3309.subsystems.Drive;

import edu.wpi.first.wpilibj.Timer;

/**
 * @author Chase.Blagden
 *         <p>
 *         Defines drive actions, can take any drive controller
 */
public class DriveAction {

	private Controller driveController;
	private double timeout;
	private Timer driveTimer = new Timer();

	public DriveAction(Controller c, double timeout) {
		this.driveController = c;
		this.timeout = timeout;
	}

	/*
	 * <p>Sets controller for drive while checking timer
	 * 
	 * @throws AutoTimedOutException
	 */
	public void updateDrive() throws AutoTimedOutException {
		if (!driveController.isCompleted()) {
			Drive.getInstance().setController(driveController);
		} else {
			Drive.getInstance().stopDrive();
		}
	}

	public void startDrive() {
		Drive.getInstance().initAuto();
		driveTimer.start();
	}

}
