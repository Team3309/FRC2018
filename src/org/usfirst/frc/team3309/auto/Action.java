package org.usfirst.frc.team3309.auto;

import org.usfirst.team3309.subsystems.Drive;

/**
 * @author Chase.Blagden Template for defining action to be run during auto
 */
public abstract class Action {

	// where action will run (inches)
	private double goal;

	/*
	 * @param double goal - where action will run (inches)
	 */
	public Action(double goal) {
		this.goal = goal;
	}

	public abstract void runAction();

	/*
	 * Converts goal in inches to encoder counts for external use
	 * 
	 * @see AutoRoutine.java
	 */
	public double getGoal() {
		return Drive.getInstance().getEncoderCounts(goal);
	}

}
