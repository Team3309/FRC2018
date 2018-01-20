package org.usfirst.frc.team3309.commands.subsystems;

public class AssemblyPosition {

    private final double beltBarPos;
    private final double elevatorPos;

    public AssemblyPosition(double beltBarPos, double elevatorPos) {
        this.beltBarPos = beltBarPos;
        this.elevatorPos = elevatorPos;
    }

    public double getElevatorPos() {
        return elevatorPos;
    }

    public double getBeltBarPos() {
        return beltBarPos;
    }

}
