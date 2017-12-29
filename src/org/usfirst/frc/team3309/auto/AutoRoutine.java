package org.usfirst.frc.team3309.auto;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.team3309.subsystems.Drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import library.ChaseTimer;

/**
 * @author Chase.Blagden
 *         <p>
 *         template for defining auto mode
 */
public abstract class AutoRoutine {

	private Timer autoTimer = new Timer();
	private DriveAction driveAction;
	private List<Action> actions = new ArrayList<>();

	public void initialize() {
		// checks for alliance color in autonomous
		if (DriverStation.getInstance().isAutonomous()) {
			switch (DriverStation.getInstance().getAlliance()) {
			case Blue:
				blueRoutine();
			case Red:
				redRoutine();
			default:
				break;
			}
		}
		if (driveAction != null) {
			driveAction.startDrive();
		}
		autoTimer.start();
	}

	public void update() {

		if (driveAction != null) {
			try {
				driveAction.updateDrive();
			} catch (AutoTimedOutException e) {
				e.printStackTrace();
			}
		}

		Action curAction = null;
		double closeness = Double.MAX_VALUE;

		// checks for current action to run, if any
		for (Action action : actions) {

			if (action.getGoal() > Drive.getInstance().getDistanceTraveled()) {
				if (Math.abs(Math.abs(action.getGoal()) - Math.abs(Drive.getInstance().getDistanceTraveled())) < Math
						.abs(closeness)) {
					closeness = Math
							.abs(Math.abs(action.getGoal()) - Math.abs(Drive.getInstance().getDistanceTraveled()));
					curAction = action;
				}
			}

		}

		if (curAction != null) {
			curAction.runAction();
			actions.remove(curAction);
		}

		/*
		 * if (autoTimer.get() > 14.9) { try { throw new
		 * AutoTimedOutException(); } catch (AutoTimedOutException e) {
		 * e.printStackTrace(); break; } }
		 */

		ChaseTimer.delayMs(100);
	}

	/*
	 * <p>Defines actions for auto, addAction and addDriveAction should be
	 * called here
	 */
	public abstract void redRoutine();

	public abstract void blueRoutine();


	public void addAction(Action a) {
		actions.add(a);
	}

	public void addDriveAction(DriveAction d) {
		driveAction = d;
	}

}
