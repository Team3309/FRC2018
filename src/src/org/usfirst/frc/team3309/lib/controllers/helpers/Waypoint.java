package src.org.usfirst.frc.team3309.lib.controllers.helpers;


public class Waypoint {

    protected double angle;
    protected double radius;

    public Waypoint(double radius, double angle) {
        this.angle = angle;
        this.radius = radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getAngle() { return angle; }

}
