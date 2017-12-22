package org.usfirst.frc.team3309.auto;

import java.util.LinkedList;
import java.util.List;

import org.usfirst.team3309.subsystems.Drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Timer;

public abstract class AutoRoutine implements Runnable {

	private Thread t;
	private static Action curAction;
	private static double mCloseness = Double.MAX_VALUE;
	private Timer mTimer = new Timer();
	private List<Action> actions = new LinkedList<>();
	
	public Thread getThread() {
		if (t == null) {
			t = new Thread(this) {
				@Override
				public void start() {
					super.start();
					if (DriverStation.getInstance().isAutonomous() && 
							DriverStation.getInstance().getAlliance() == Alliance.Blue) {
						blueRoutine();
					} else if (DriverStation.getInstance().isAutonomous() && 
							DriverStation.getInstance().getAlliance() == Alliance.Red) {
						redRoutine();
					}
					for (Action action : actions) {
						action.getThread().start();
					}
				}
				@Override
				public void run() {
					if (curAction != null) {
						curAction.runAction();
						// trying to figure out how to kill it
					}
					if (mTimer.get() > 14.9) {
						for (Action action : actions) {
							action.getThread().stop();
						}
						this.stop();
					}
				}
			
			};
		}
		return t;
	}
	
	public AutoRoutine() {
		t = new Thread(this);
	}

	public abstract void redRoutine();

	public abstract void blueRoutine();
	

	public void addAction(Action a) {
		actions.add(a);
	}
	
	public static void setCurAction(Action a) {
		curAction = a;
	}
	
	public static void setCloseness(double closeness) {
		mCloseness = closeness;
	}
	
	public static double getCloseness() {
		return mCloseness;
	}
	
	public static double getPosition() {
		double pos = Drive.getInstance().getDistanceTraveled();
		return pos;
	}
	

}
