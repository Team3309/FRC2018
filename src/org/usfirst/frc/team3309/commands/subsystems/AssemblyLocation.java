package org.usfirst.frc.team3309.commands.subsystems;

public enum AssemblyLocation {

    GROUND(2115, 0),
    SCALE_DOWN(3100, 20000),
    SCALE_UP(3100, 42000),

    SWITCH(3100, 10000),
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
