package library.controllers.drive;

import org.usfirst.team3309.subsystems.Drive;

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
	
	public void convertToEncoderCounts() {
		this.x = Drive.getInstance().getEncoderCounts(x);
		this.y = Drive.getInstance().getEncoderCounts(y);
	}
	
}
