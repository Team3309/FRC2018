package org.usfirst.frc.team3309.auto;


/**
 * @author Chase.Blagden
 *
 */
public abstract class Action  {
	
	private double goal;
	
	public Action(double goal) {
		this.goal = goal;
	}
	
	public abstract void runAction();
	
	public double getGoal() {
		return goal;
	}

}
