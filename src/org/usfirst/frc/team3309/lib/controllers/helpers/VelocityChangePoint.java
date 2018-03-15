package org.usfirst.frc.team3309.lib.controllers.helpers;

public class VelocityChangePoint {

    private double leftVel;
    private double rightVel;

    private double pos;

    public VelocityChangePoint(double vel, double pos) {
        this(vel, vel, pos);
    }

    public VelocityChangePoint(double leftVel, double rightVel, double pos) {
        this.leftVel = leftVel;
        this.rightVel = rightVel;
        this.pos = pos;
    }

    public double getLeftVel() {
        return leftVel;
    }

    public double getRightVel() {
        return rightVel;
    }

    public double getPosToChangeAt() {
        return pos;
    }

    @Override
    public String toString() {
        return "VelocityChangePoint{" +
                "leftVel=" + leftVel +
                ", rightVel=" + rightVel +
                ", pos=" + pos +
                '}';
    }
}