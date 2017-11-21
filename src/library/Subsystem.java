package library;

import org.usfirst.frc.team3309.robot.Systems;

/*
 * Basic setup for uncontrolled subsystem, implements Runnable to allow 
 * 			for multithreading in subsystems during teleop
 * @author Chase Blagden
 * */
public abstract class Subsystem implements Runnable {

	private String subsystemID = "default name";
	
	public Subsystem(String name) {
		this.subsystemID = name;
	}
	
	/*
	 * only to be run during teleop 
	 * */
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
