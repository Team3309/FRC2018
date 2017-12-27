package library.actuators;

import edu.wpi.first.wpilibj.Spark;

public class SparkMC extends Actuator {

	private Spark spark;
	private boolean isReversed = false;
	private double desiredOutput = 0.0;

	public SparkMC(int port) {
		spark = new Spark(port);
	}

	@Override
	protected void output() {
		this.spark.set(desiredOutput * (isReversed ? 1 : -1));
	}

	public void isReversed(boolean isReversed) {
		this.isReversed = isReversed;
	}

	public boolean isReversed() {
		return this.isReversed;
	}

	public void setDesiredOutput(double desiredOutput) {
		this.desiredOutput = desiredOutput;
	}

	public double getDesiredOutput() {
		return this.desiredOutput;
	}

}
