package org.usfirst.frc.team3309.commands.subsystems;

import org.usfirst.frc.team3309.robot.Constants;

public enum AssemblyLocation {

    BOTTOM(Constants.BELTBAR_BOTTOM_POS, Constants.ELEVATOR_BOTTOM_POS),
    BOTTOM_FOR_CUBE(Constants.BELTBAR_INTAKE_POS, Constants.ELEVATOR_INTAKE_POS),
    EXCHANGE_ZONE(Constants.BELTBAR_EXCHANGE_POS, Constants.ELEVATOR_EXCHANGE_POS),
    SCALE_DOWN(Constants.BELTBAR_EJECT_POS, Constants.ELEVATOR_SCALE_DOWN_POS),
    SCALE_MIDDLE(Constants.BELTBAR_EJECT_POS, Constants.ELEVATOR_SCALE_MIDDLE_POS),
    SCALE_UP(Constants.BELTBAR_EJECT_POS, Constants.ELEVATOR_SCALE_TOP_POS),
    SWITCH(Constants.BELTBAR_SWITCH_POS, Constants.ELEVATOR_SWITCH_POS),
    CLIMB(Constants.BELTBAR_CLIMB, Constants.ELEVATOR_BOTTOM_POS);

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
