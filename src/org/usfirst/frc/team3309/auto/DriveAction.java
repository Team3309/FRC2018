package org.usfirst.frc.team3309.auto;

import library.controllers.Controller;

import org.usfirst.team3309.subsystems.Drive;

import edu.wpi.first.wpilibj.Timer;

/**
 * @author Chase.Blagden
 * Defines drive actions, can take any drive controller
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
	 * Sets controller for drive while checking timer
	 * @thorws AutoTimedOutException
	 * */
	public void updateDrive() throws AutoTimedOutException {
		if (!driveController.isCompleted()) {
			Drive.getInstance().setController(driveController);
		} else {
			Drive.getInstance().stopDrive();
		}
		if (driveTimer.get() > timeout) {
			throw new AutoTimedOutException();
		}
	}

	public void startDrive() {
		Drive.getInstance().initAuto();
		driveTimer.start();
	}

}
