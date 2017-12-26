package library;

import org.usfirst.frc.team3309.robot.Systems;

/*
 * Basic setup for uncontrolled subsystem, implements Runnable to allow 
 * 			for multithreading in subsystems during teleop
 * @author Chase Blagden
 * */
public abstract class Subsystem implements Runnable {

	protected String subsystemID = "default name";
	
	public Subsystem(String name) {
		this.subsystemID = name;
		Systems.add(this);
	}
	
	/*
	 * only to be run during teleop 
	 * */
	public void run() {
		updateTeleop();
	}
	
	public abstract void initTeleop();
	
	public abstract void updateTeleop();
	
	public abstract void init();
	
	public abstract void updateAuto();
	
	public abstract void initAuto();
	
	public abstract void manualControl();
	
	public abstract void sendToDashboard();
	
	public String getName() {
		return subsystemID;
	}
	
	
}
