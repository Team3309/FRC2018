package org.usfirst.frc.team3309.commands.subsystems;

public enum AssemblyLocation {

    BOTTOM(3300, 0),
    BOTTOM_FOR_CUBE(1950, 1200),
    SCALE_DOWN(3300, 34000),
    SCALE_MIDDLE(3300, 38000),
    SCALE_UP(3300, 42000),
    SWITCH(3300, 10000);

    private double beltBarPosition;
    private double elevatorPosition;

    AssemblyLocation(double beltBarPosition, double elevatorPosition) {
        this.beltBarPosition = beltBarPosition;
        this.elevatorPosition = elevatorPosition;
    }

    public double getBeltBarPosition() { return beltBarPosition; }

    public double getElevatorPosition() { return elevatorPosition; }

}
