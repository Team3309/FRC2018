package org.usfirst.frc.team3309.commands.subsystems;

public enum AssemblyLocation {

    GROUND(0, 0),
    SCALE_DOWN(0, 20000),
    SCALE_UP(0, 42000),
    SWITCH(-400, 10000),
    HOLD_CUBE(0, 0),
    CLIMB_MODE(0, 0);

    private double beltBarPosition;
    private double elevatorPosition;

    AssemblyLocation(double beltBarPosition, double elevatorPosition) {
        this.beltBarPosition = beltBarPosition;
        this.elevatorPosition = elevatorPosition;
    }

    public double getBeltBarPosition() { return beltBarPosition; }

    public double getElevatorPosition() { return elevatorPosition; }

}
