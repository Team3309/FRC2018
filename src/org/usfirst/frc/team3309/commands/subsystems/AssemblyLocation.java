package org.usfirst.frc.team3309.commands.subsystems;

public enum AssemblyLocation {

    BOTTOM(-3000, 0),
    BOTTOM_FOR_CUBE(-1700, 900),
    EXCHANGE_ZONE(-2000, 1300),
    SCALE_DOWN(-2500, 34000),
    SCALE_MIDDLE(-2500, 38000),
    SCALE_UP(-2500, 42000),
    SWITCH(-2500, 14000),
    CLIMB(-3000, 43000);

    private double beltBarPosition;
    private double elevatorPosition;

    AssemblyLocation(double beltBarPosition, double elevatorPosition) {
        this.beltBarPosition = beltBarPosition;
        this.elevatorPosition = elevatorPosition;
    }

    public double getBeltBarPosition() { return beltBarPosition; }

    public double getElevatorPosition() { return elevatorPosition; }

}
