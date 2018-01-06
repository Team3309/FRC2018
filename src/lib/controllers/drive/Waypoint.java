package lib.controllers.drive;


public class Waypoint {

    protected double x;
    protected double y;
    protected double radius;

    public Waypoint() {
        this.x = 0.0;
        this.y = 0.0;
        this.radius = 0.0;
    }

    public Waypoint(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

}
