package org.usfirst.frc.team3309.commands.subsystems;

public enum AssemblyLocation {

    // comp bot
    /*BOTTOM(-1950, 0),
    BOTTOM_FOR_CUBE(-640, 940),
    EXCHANGE_ZONE(-900, 700),
    SCALE_DOWN(-1690, 34000),
    SCALE_MIDDLE(-1690, 38000),
    SCALE_UP(-1690, 42000),
    SWITCH(-1090, 14000);*/

   //  practice bot
    BOTTOM(-3000, 0),
    BOTTOM_FOR_CUBE(-1700, 900),
    EXCHANGE_ZONE(-2000, 1300),
    SCALE_DOWN(-2500, 34000),
    SCALE_MIDDLE(-2500, 38000),
    SCALE_UP(-2500, 42000),
    SWITCH(-2500, 14000),
    CLIMB(-3000, 15000);

    private double beltBarPosition;
    private double elevatorPosition;

    AssemblyLocation(double beltBarPosition, double elevatorPosition) {
        this.beltBarPosition = beltBarPosition;
        this.elevatorPosition = elevatorPosition;
    }

    public double getBeltBarPosition() {
        return beltBarPosition;
    }

    public double getElevatorPosition() {
        return elevatorPosition;
    }

}
