package org.usfirst.frc.team3309.auto;

public abstract class Action implements Runnable {
	
	protected double goal;
	private Thread t;
	
	public Action(double goal) {
		this.goal = goal;
	}
	
	public Thread getThread() {
		if (t == null) {
			t = new Thread(this) {
				@Override
				public void run() {
					if (goal > AutoRoutine.getPosition()) {
						double closeness = goal - AutoRoutine.getPosition();
						if (closeness < AutoRoutine.getCloseness()) {
							AutoRoutine.setCloseness(closeness);
							AutoRoutine.setCurAction(Action.this);
						}
					}
				}
			};
		}
		return t;
	}
	
	public abstract void runAction();		

}
