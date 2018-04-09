package org.usfirst.frc.team3309.lib.controllers.helpers;

public class DriveState {

    private double leftPos;
    private double rightPos;
    private double angPos;

    public DriveState(double leftPos, double rightPos, double angPos) {
        this.leftPos = leftPos;
        this.rightPos = rightPos;
        this.angPos = angPos;
    }

    public DriveState(double curPos, double angPos) {
        this(curPos, curPos, angPos);
    }

    public double getAveragePos() {
        return (leftPos + rightPos) / 2.0;
    }

    public double getLeftPos() {
        return leftPos;
    }

    public double getRightPos() {
        return rightPos;
    }

    public double getAngPos() {
        return angPos;
    }
}
