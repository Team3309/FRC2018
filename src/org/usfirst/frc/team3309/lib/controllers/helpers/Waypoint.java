package org.usfirst.frc.team3309.lib.controllers.helpers;


import org.usfirst.frc.team3309.lib.Length;

public class Waypoint {

    protected double angle;
    protected double radius;

    public Waypoint(Length radius, double angle) {
        this.angle = angle;
        this.radius = radius.toInches();
    }

    public void setRadius(Length radius) {
        this.radius = radius.toInches();
    }

    public double getRadius() {
        return radius;
    }

    public double getAngle() { return angle; }

}
