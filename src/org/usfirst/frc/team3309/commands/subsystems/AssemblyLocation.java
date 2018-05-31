package org.usfirst.frc.team3309.commands.subsystems;

import org.usfirst.frc.team3309.robot.Constants;

public class AssemblyLocation {

    public static AssemblyLocation BOTTOM;
    public static AssemblyLocation INTAKE;
    public static AssemblyLocation EXCHANGE_ZONE;
    public static AssemblyLocation SCALE_DOWN;
    public static AssemblyLocation SCALE_MIDDLE;
    public static AssemblyLocation SCALE_UP;
    public static AssemblyLocation SWITCH;
    public static AssemblyLocation CLIMB;

    private double beltBarPosition;
    private double elevatorPosition;

    public static void init()
    {
        BOTTOM = new AssemblyLocation(Constants.BELTBAR_BOTTOM_POS, Constants.ELEVATOR_BOTTOM_POS);
        INTAKE = new AssemblyLocation(Constants.BELTBAR_INTAKE_POS, Constants.ELEVATOR_INTAKE_POS);
        EXCHANGE_ZONE = new AssemblyLocation(Constants.BELTBAR_EXCHANGE_POS, Constants.ELEVATOR_EXCHANGE_POS);
        SCALE_DOWN = new AssemblyLocation(Constants.BELTBAR_EJECT_POS, Constants.ELEVATOR_SCALE_DOWN_POS);
        SCALE_MIDDLE = new AssemblyLocation(Constants.BELTBAR_EJECT_POS, Constants.ELEVATOR_SCALE_MIDDLE_POS);
        SCALE_UP = new AssemblyLocation(Constants.BELTBAR_SCALE_UP_POS, Constants.ELEVATOR_SCALE_TOP_POS);
        SWITCH = new AssemblyLocation(Constants.BELTBAR_SWITCH_POS, Constants.ELEVATOR_SWITCH_POS);
        CLIMB = new AssemblyLocation(Constants.BELTBAR_CLIMB, Constants.ELEVATOR_BOTTOM_POS);
    }

    private AssemblyLocation(double beltBarPosition, double elevatorPosition) {
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
