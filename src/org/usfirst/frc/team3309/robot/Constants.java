package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.lib.math.Length;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;

public class Constants {

    private static final byte[] PRACTICEBOT_MAC_ADDR = {0x00, (byte) 0x80, 0x2F, 0x17, (byte) 0x85, (byte) 0xD3};
    private static final byte[] COMPBOT_MAC_ADDR = {0x00, (byte)0x80, 0x2F, 0x17, (byte) 0xE4, 0x5E}; // find this at comp

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

    public static int JOYSTICK_TRIGGER_BUTTON = 1;

    public static double BELTBAR_BOTTOM_POS = Constants.currentRobot == Robot.PRACTICE ? -2680 : -2700;
    public static double BELTBAR_INTAKE_POS = Constants.currentRobot == Robot.PRACTICE ? -1550 : -1300;
    public static double BELTBAR_EXCHANGE_POS = Constants.currentRobot == Robot.PRACTICE ? -1800 : -1700;
    public static double BELTBAR_EJECT_POS = Constants.currentRobot == Robot.PRACTICE ? -2400 : -2300;
    public static double BELTBAR_SCALE_UP_POS = Constants.currentRobot == Robot.PRACTICE ? -2550 : -2500;
    public static double BELTBAR_SWITCH_POS = Constants.currentRobot == Robot.PRACTICE ? -2000 : -1900;
    public static double BELTBAR_CLIMB = Constants.currentRobot == Robot.PRACTICE ? -2800 : -2700;

    public static double ELEVATOR_BOTTOM_POS = 0;
    public static double ELEVATOR_INTAKE_POS = 1100;
    public static double ELEVATOR_EXCHANGE_POS = 700;
    public static double ELEVATOR_SWITCH_POS = 15000;
    public static double ELEVATOR_SCALE_DOWN_POS = 28000;
    public static double ELEVATOR_SCALE_MIDDLE_POS = 33000;
    public static double ELEVATOR_SCALE_TOP_POS = 40000;

    // drive
    public static final int DRIVE_RIGHT_0_ID = 11;
    public static final int DRIVE_RIGHT_1_ID = 13;
    public static final int DRIVE_RIGHT_2_ID = 15;

    public static final int DRIVE_LEFT_0_ID = 10;
    public static final int DRIVE_LEFT_1_ID = 12;
    public static final int DRIVE_LEFT_2_ID = 14;

    public static final int DRIVE_SHIFTER = 7;

    // roller
    public static final int ROLLER_LEFT = 40;
    public static final int ROLLER_RIGHT = 41;

    // arms
    public static final int ARMS_ACTUATOR_A = 2;
    public static final int ARMS_ACTUATOR_B = 1;

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

    public static final double LIFT_NUDGE_SPEED = 1800;

    // beltbar
    public static final int BELTBAR_0 = 30;

    public static final int BELTBAR_SHARP_SENSOR_LEFT = 0;
    public static final int BELTBAR_SHARP_SENSOR_RIGHT = 1;

    public static final int BELTBAR_HALL_EFFECT = 2;

    // falcon doors
    public static final int FALCONDOORS_SOLENOID = 4;

    // robot constants
    public static final double AUTO_ROLLER_INTAKE_POWER = -1;

    public static final double DRIVE_ENCODER_COUNTS_PER_REV = 4096*9.6;
    public static final double WHEEL_DIAMETER_INCHES = 6.0;
    public static final Length WHEELBASE_INCHES = Length.fromInches(26.0);

}
