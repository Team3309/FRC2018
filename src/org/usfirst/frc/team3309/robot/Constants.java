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

    // shooter
    public static final int SHOOTER_SHIFTER = 5;

    // falcon doors
    public static final int FALCONDOORS_SOLENOID = 4;

    // robot constants

    public static final double DRIVE_ENCODER_COUNTS_PER_REV = 4096.0 * 5  * 2 * 10;
    public static final Length WHEEL_DIAMETER_INCHES = Length.fromInches(6.0);
    public static final Length WHEELBASE_INCHES = Length.fromInches(26.0);

    public static final ArrayList<Waypoint> curvyToSwitchLeft = new ArrayList<>();
    public static final ArrayList<Waypoint> curvyToSwitchRight = new ArrayList<>();
    public static final ArrayList<Waypoint> turnRightAngleLeft = new ArrayList<>();

    static  {
        curvyToSwitchRight.add(new Waypoint(52.854731086091235,-0.8675442323770085));
        curvyToSwitchRight.add(new Waypoint(-284.1884696605429,0.17186991901218243));
        curvyToSwitchRight.add(new Waypoint(-106.49401217174581,0.35621490596844907));
        curvyToSwitchRight.add(new Waypoint(-127.06675597446096,0.2994807202730872));
        curvyToSwitchRight.add(new Waypoint(-11.533333333333331,4.407090323594159));
        curvyToSwitchRight.add(new Waypoint(11.533333333333331,-4.407090323594159));

        curvyToSwitchLeft.add(new Waypoint(-103.91292904800127,0.818334867744188));
        curvyToSwitchLeft.add(new Waypoint(98.47224413937104,-0.8583135548674871));
        curvyToSwitchLeft.add(new Waypoint(121.30000000000015,-6.079807603653432));
        curvyToSwitchLeft.add(new Waypoint(-121.30000000000015,6.0798076036534345));

        turnRightAngleLeft.add(new Waypoint(-133.26484176551588,0.6127649414338202));
        turnRightAngleLeft.add(new Waypoint(80.19104427606113,0.9680110939597296));
    }

}
