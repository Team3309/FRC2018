package org.usfirst.frc.team3309.lib.controllers.helpers;

public class DriveSignal {

    private final double leftMotor;
    private final double rightMotor;

    public DriveSignal(double leftMotor, double rightMotor) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
    }

    public double getRightMotor() {
        return rightMotor;
    }

    public double getLeftMotor() {
        return leftMotor;
    }
}
