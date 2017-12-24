package org.usfirst.frc.team3309.robot;

import java.util.ArrayList;
import java.util.List;

import library.Subsystem;

public class Systems {

	private static List<Subsystem> subsystems = new ArrayList<Subsystem>();
	
	public static void add(Subsystem s) {
		subsystems.add(s);
	}
	
	public static void init() {
		for (Subsystem s : subsystems) {
			s.init();
		}
	}
	
	public static void initTeleop() {
		for (Subsystem s : subsystems) {
			new Thread(s).start();
		}
	}
	
	public static void updateAuto() {
		for (Subsystem s : subsystems) {
			s.updateAuto();
		}
	}
	
	public static void sendToDashboard() {
		for (Subsystem s : subsystems) {
			s.sendToDashboard();
		}
	}
	
}
