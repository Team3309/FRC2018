package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.lib.controllers.helpers.Waypoint;
import org.usfirst.frc.team3309.lib.math.Length;

import java.util.ArrayList;

public class Constants {

    // drive
    public static final int DRIVE_RIGHT_0_ID = 11;
    public static final int DRIVE_RIGHT_1_ID = 13;
    public static final int DRIVE_RIGHT_2_ID = 15;

    public static final int DRIVE_LEFT_0_ID = 10;
    public static final int DRIVE_LEFT_1_ID = 12;
    public static final int DRIVE_LEFT_2_ID = 14;

    public static final int SHIFTER = 7;

    // roller
    public static final int ROLLER_LEFT = 40;
    public static final int ROLLER_RIGHT = 41;

    // arms
    public static final int ARMS_LEFT_ACTUATOR_A = 1;
    public static final int ARMS_LEFT_ACTUATOR_B = 2;

    public static final int ARMS_RIGHT_ACTUATOR_A = 3;
    public static final int ARMS_RIGHT_ACTUATOR_B = 0;


    // lift
    public static final int LIFT_0 = 20;
    public static final int LIFT_1 = 21;
    public static final int LIFT_2 = 22;
    public static final int LIFT_3 = 23;
    public static final int LIFT_4 = 24;

    public static final int LIFT_SHIFTER = 6;

    public static final int LIFT_BANNER_SENSOR = 0;

    // beltbar
    public static final int BELTBAR_0 = 30;

    public static final int BELTBAR_SHARP_SENSOR = 0;
    public static final int BELTBAR_HALL_EFFECT = 1;

    // shooter
    public static final int SHOOTER_SHIFTER = 5;

    // falcon doors
    public static final int FALCONDOORS_SOLENOID = 4;

    // robot constants

    public static final double DRIVE_ENCODER_COUNTS_PER_REV = 4096.0 * 5  * 2 * 10;
    public static final Length WHEEL_DIAMETER_INCHES = Length.fromInches(6.0);
    public static final Length WHEELBASE_INCHES = Length.fromInches(26.0);

    public static final ArrayList<Waypoint> curvyToSwitchRight = new ArrayList<>();
    public static final ArrayList<Waypoint> curvyToSwitchLeft = new ArrayList<>();

    static  {
        curvyToSwitchRight.add(new Waypoint(82.34987618525085, 0.39093873144451713));
        curvyToSwitchRight.add(new Waypoint(-29.777276396478868,     0.740486358108046));

        curvyToSwitchLeft.add(new Waypoint(-82.34987618525085, -0.39093873144451713));
        curvyToSwitchLeft.add(new Waypoint(34.777276396478868,     -0.740486358108046));
    }

}
