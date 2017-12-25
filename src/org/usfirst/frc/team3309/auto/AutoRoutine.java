package org.usfirst.frc.team3309.auto;

import java.util.LinkedList;
import java.util.List;

import library.ChaseTimer;

import org.usfirst.team3309.subsystems.Drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Timer;

/**
 * @author Chase.Blagden template for defining auto mode, every auto is run in
 *         its own thread
 */
@SuppressWarnings("deprecation")
public abstract class AutoRoutine implements Runnable {

	private Thread t;
	private DriveAction driveAction;
	private Timer autoTimer = new Timer();
	private List<Action> actions = new LinkedList<>();

	public AutoRoutine() {
		t = new Thread(this) {

			@Override
			public void start() {
				super.start();

				// checks for alliance color in autonomous
				if (DriverStation.getInstance().isAutonomous()) {
					if (DriverStation.getInstance().getAlliance() == Alliance.Blue) {
						blueRoutine();
					} else if (DriverStation.getInstance().getAlliance() == Alliance.Red) {
						redRoutine();
					} else {
						this.stop();
					}
				} else {
					this.stop();
				}

				if (driveAction != null) {
					driveAction.startDrive();
				}

				autoTimer.start();
			}

		};
	}

	/*
	 * Main loop running auto, updating drive and performing actions.
	 * 
	 * @see Systems.java
	 */
	@Override
	public void run() {

		if (driveAction != null) {
			try {
				driveAction.updateDrive();
			} catch (AutoTimedOutException e) {
				e.printStackTrace();
				driveAction = null;
			}
		}

		Action curAction = null;
		double closeness = Double.MAX_VALUE;

		// checks for current action to run, if any
		for (Action action : actions) {
			if (action.getGoal() > Drive.getInstance().getDistanceTraveled()) {
				if (Math.abs(Math.abs(action.getGoal())
						- Math.abs(Drive.getInstance().getDistanceTraveled())) < Math
						.abs(closeness)) {
					closeness = Math.abs(Math.abs(action.getGoal())
							- Math.abs(Drive.getInstance()
									.getDistanceTraveled()));
					curAction = action;
				}
			}
		}

		if (curAction != null) {
			curAction.runAction();
			actions.remove(curAction);
		}

		if (autoTimer.get() > 14.9) {
			t.stop();
			try {
				throw new AutoTimedOutException();
			} catch (AutoTimedOutException e) {
				e.printStackTrace();
			}
		}

		ChaseTimer.delayMs(100);

	}

	/*
	 * Defines actions for auto, addAction and addDriveAction should be called
	 * here
	 */
	public abstract void redRoutine();

	public abstract void blueRoutine();

	public void startAutoThread() {
		t.start();
	}

	public void stopAutoThread() {
		t.stop();
	}

	public void addAction(Action a) {
		actions.add(a);
	}

	public void addDriveAction(DriveAction d) {
		driveAction = d;
	}

}
