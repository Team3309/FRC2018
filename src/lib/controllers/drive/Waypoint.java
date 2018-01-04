package lib.controllers.drive;


public class Waypoint {

	protected double x;
	protected double y;
	protected double heading;

	public Waypoint() {
		this.x = 0.0;
		this.y = 0.0;
		this.heading = 0.0;
	}

	public Waypoint(double x, double y, double heading) {
		this.x = x;
		this.y = y;
		this.heading = heading;
	}

	public static double getDistance(Waypoint a, Waypoint b) {
		return Math.hypot(a.x - b.x, a.y - b.y);
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

}
