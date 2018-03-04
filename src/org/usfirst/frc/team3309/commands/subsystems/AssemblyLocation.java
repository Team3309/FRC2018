package org.usfirst.frc.team3309.commands.subsystems;

public enum AssemblyLocation {

    BOTTOM(-3100, 0),
    BOTTOM_FOR_CUBE(-1700, 900),
    SCALE_DOWN(-3100, 34000),
    SCALE_MIDDLE(-3100, 38000),
    SCALE_UP(-3100, 42000),
    SWITCH(-3100, 10000);

    private double beltBarPosition;
    private double elevatorPosition;

    AssemblyLocation(double beltBarPosition, double elevatorPosition) {
        this.beltBarPosition = beltBarPosition;
        this.elevatorPosition = elevatorPosition;
    }

    public double getBeltBarPosition() { return beltBarPosition; }

    public double getElevatorPosition() { return elevatorPosition; }

}
