package lib.controllers.drive;


public class Waypoint {

	protected double x;
	protected double y;

	public Waypoint() {
		this.x = 0.0;
		this.y = 0.0;
	}

	public Waypoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Waypoint add(Waypoint w) {
		return new Waypoint(x + w.x, y + w.y);
	}

	public Waypoint subtract(Waypoint w) {
		return new Waypoint(x - w.x, y - w.y);
	}

	public Waypoint convertToEncoderCounts() {
		return new Waypoint(x, y);
	}

	public static double getDistance(Waypoint a, Waypoint b) {
		return Math.hypot(a.x - b.x, a.y - b.y);
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

}
