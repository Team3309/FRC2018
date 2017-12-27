package org.usfirst.frc.team3309.auto;

import library.controllers.drive.Waypoint;

import org.usfirst.team3309.subsystems.Drive;

/**
 * @author Chase.Blagden
 *         <p>
 *         Template for defining action to be run during auto
 *  TODO: modify goal to take in coordinates on field      
 */
public abstract class Action {

	// where action will run (inches)
	private double goal;
	
	private Waypoint goalPoint;

	/*
	 * @param double goal - where action will run (inches)
	 */
	public Action(double goal) {
		this.goal = goal;
	}
	
	public Action(Waypoint goalPoint) {
		this.goalPoint = goalPoint;
	}

	public abstract void runAction();

	/*
	 * <p>Converts goal in inches to encoder counts for external use
	 * 
	 * @see AutoRoutine.java
	 */
	public double getGoal() {
		return Drive.getInstance().getEncoderCounts(goal);
	}
	
	public Waypoint getPoint() {
		return goalPoint.convertToEncoderCounts();
	}

}
