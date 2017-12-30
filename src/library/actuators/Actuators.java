package library.actuators;

import java.util.ArrayList;
import java.util.List;

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
