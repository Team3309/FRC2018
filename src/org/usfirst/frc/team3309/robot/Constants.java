package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.lib.controllers.helpers.Waypoint;
import org.usfirst.frc.team3309.lib.math.Length;

import java.net.NetworkInterface;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;

public class Constants {

    private static final byte[] PRACTICEBOT_MAC_ADDR = {0x00, (byte) 0x80, 0x2F, 0x17, (byte) 0x85, (byte) 0xD3};
    private static final byte[] COMPBOT_MAC_ADDR = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00}; // find this at comp

    public enum Robot {
        PRACTICE,
        COMPETITION
    }

    public static Robot currentRobot;

    static {
        try {
            byte[] rioMac = NetworkInterface.getByName("eth0").getHardwareAddress();
            if (Arrays.equals(rioMac, PRACTICEBOT_MAC_ADDR)) {
                currentRobot = Robot.PRACTICE;
            } else if (Arrays.equals(rioMac, COMPBOT_MAC_ADDR)) {
                currentRobot = Robot.COMPETITION;
            } else {
                currentRobot = null;
                System.err.println("Oh no! Unknown robot! Did somebody install a new rio?");
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
    }

    public static double BELTBAR_BOTTOM_POS = Constants.currentRobot == Robot.PRACTICE ? -2720 : -1950;
    public static double BELTBAR_INTAKE_POS = Constants.currentRobot == Robot.PRACTICE ? -1350 : -640;
    public static double BELTBAR_EXCHANGE_POS = Constants.currentRobot == Robot.PRACTICE ? -1800 : -900;
    public static double BELTBAR_EJECT_POS = Constants.currentRobot == Robot.PRACTICE ? -2400 : -1690;
    public static double BELTBAR_SWITCH_POS = Constants.currentRobot == Robot.PRACTICE ? -2000 : -1090;
    public static double BELTBAR_CLIMB = Constants.currentRobot == Robot.PRACTICE ? -2800 : -1950;

    public static double ELEVATOR_BOTTOM_POS = 0;
    public static double ELEVATOR_INTAKE_POS = 1100;
    public static double ELEVATOR_EXCHANGE_POS = 700;
    public static double ELEVATOR_SWITCH_POS = 15000;
    public static double ELEVATOR_SCALE_DOWN_POS = 30000;
    public static double ELEVATOR_SCALE_MIDDLE_POS = 38000;
    public static double ELEVATOR_SCALE_TOP_POS = 42000;

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
    public static final int ARMS_ACTUATOR_A = 1;
    public static final int ARMS_ACTUATOR_B = 2;

    // lift
    public static final int LIFT_0 = 20;
    public static final int LIFT_1 = 21;
    public static final int LIFT_2 = 22;
    public static final int LIFT_3 = 23;
    public static final int LIFT_4 = 24;

    public static final int LIFT_SHIFTER = 6;

    public static final int LIFT_BANNER_SENSOR = 0;

    public static final int LIFT_HOLDER_A = 3;
    public static final int LIFT_HOLDER_B = 0;

    // beltbar
    public static final int BELTBAR_0 = 30;

    public static final int BELTBAR_SHARP_SENSOR_LEFT = 0;
    public static final int BELTBAR_SHARP_SENSOR_RIGHT = 1;

    public static final int BELTBAR_HALL_EFFECT = 2;

    // shooter
    public static final int SHOOTER_SHIFTER = 5;

    // falcon doors
    public static final int FALCONDOORS_SOLENOID = 4;

    // robot constants

    public static final double MAX_LIFT_POS = 47000;

    public static final double DRIVE_ENCODER_COUNTS_PER_REV = 39298;
    public static final Length WHEEL_DIAMETER_INCHES = Length.fromInches(6.0);
    public static final Length WHEELBASE_INCHES = Length.fromInches(26.0);

    public static final ArrayList<Waypoint> curvyToSwitchRight = new ArrayList<>();
    public static final ArrayList<Waypoint> curvyToSwitchLeft = new ArrayList<>();

    static {
        curvyToSwitchRight.add(new Waypoint(0.796 * 82.34987618525085, 0.39093873144451713));
        curvyToSwitchRight.add(new Waypoint(-0.796 * 29.777276396478868, 0.740486358108046));

        curvyToSwitchLeft.add(new Waypoint(-0.796 * 82.34987618525085, -0.39093873144451713));
        //   curvyToSwitchLeft.add(new Waypoint(0.796 * 34.777276396478868,     -0.740486358108046));
    }

}
