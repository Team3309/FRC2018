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
		this.timeout = timeout;
		this.driveController = c;
	}

	public void updateDrive() throws AutoTimedOutException {
		Drive.getInstance().setController(driveController);
		if (driveTimer.get() > timeout) {
			throw new AutoTimedOutException();
		}
	}

	public void startDrive() {
		Drive.getInstance().initAuto();
		driveTimer.start();
	}

}
