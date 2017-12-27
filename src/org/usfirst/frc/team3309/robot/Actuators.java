package org.usfirst.frc.team3309.robot;

import java.util.ArrayList;
import java.util.List;

import library.actuators.Actuator;

public class Actuators {

	private static List<Actuator> actuators = new ArrayList<Actuator>();

	public static void addActuator(Actuator actuator) {
		actuators.add(actuator);
	}

	public static void actuate() {
		for (Actuator a : actuators) {
			a.actuate();
		}
	}

}
