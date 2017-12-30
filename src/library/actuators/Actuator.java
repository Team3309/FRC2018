package library.actuators;


public abstract class Actuator {

	protected abstract void output();

	public Actuator() {
		Actuators.addActuator(this);
	}

	public void actuate() {
		output();
	}

}
