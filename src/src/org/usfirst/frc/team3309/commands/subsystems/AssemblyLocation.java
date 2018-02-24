package src.org.usfirst.frc.team3309.commands.subsystems;

public enum AssemblyLocation {

    SCALE_UP(0, 0),
    SCALE_DOWN(0, 0),
    SWITCH_UP(0, 0),
    SWITCH_DOWN(0, 0),
    HOLD_CUBE(0, 0),
    TUCK_IN_CUBE(0, 0),
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
