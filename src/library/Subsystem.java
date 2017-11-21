package library;

import org.usfirst.frc.team3309.robot.Systems;

public abstract class Subsystem implements Runnable {

	private String subsystemID = "default name";
	
	public Subsystem(String name) {
		this.subsystemID = name;
	}
	
	@Override
	public void run() {
		updateTeleop();
		Systems.add(this);
	}
	
	public abstract void initTeleop();
	
	public abstract void updateTeleop();
	
	public abstract void init();
	
	public abstract void updateAuto();
	
	public String getName() {
		return subsystemID;
	}
	
}
