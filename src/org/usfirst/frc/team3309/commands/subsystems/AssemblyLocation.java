package org.usfirst.frc.team3309.commands.subsystems;

import org.usfirst.frc.team3309.robot.Constants;

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
    BOTTOM(Constants.BELTBAR_BOTTOM_POS, Constants.ELEVATOR_BOTTOM_POS),
    BOTTOM_FOR_CUBE(Constants.BELTBAR_BOTTOM_FOR_CUBE_POS, Constants.ELEVATOR_BOTTOM_FOR_CUBE_POS),
    EXCHANGE_ZONE(Constants.BELTBAR_EXCHANGE_POS, Constants.ELEVATOR_EXCHANGE_POS),
    SCALE_DOWN(Constants.BELTBAR_EJECT_POS, Constants.ELEVATOR_SCALE_DOWN_POS),
    SCALE_MIDDLE(Constants.BELTBAR_EJECT_POS, Constants.ELEVATOR_SCALE_MIDDLE_POS),
    SCALE_UP(Constants.BELTBAR_EJECT_POS, Constants.ELEVATOR_SCALE_TOP_POS),
    SWITCH(Constants.BELTBAR_SWITCH_POS, Constants.ELEVATOR_SWITCH_POS);

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
