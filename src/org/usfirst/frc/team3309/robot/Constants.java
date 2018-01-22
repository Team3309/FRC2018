package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.lib.Length;
import org.usfirst.frc.team3309.lib.controllers.helpers.Waypoint;

import java.util.ArrayList;

public class Constants {

    // drive
    public static final int DRIVE_RIGHT_0_ID = 2;
    public static final int DRIVE_RIGHT_1_ID = 3;
    public static final int DRIVE_RIGHT_2_ID = 4;

    public static final int DRIVE_LEFT_0_ID = 14;
    public static final int DRIVE_LEFT_1_ID = 15;
    public static final int DRIVE_LEFT_2_ID = 16;

    public static final int SHIFTER = 7;

    // intake
    public static final int INTAKE_LEFT_ROLLER = 0;
    public static final int INTAKE_RIGHT_ROLLER = 0;

    public static final int INTAKE_HARDSTOP_ACTUATOR_A = 0;
    public static final int INTAKE_HARDSTOP_ACTUATOR_B = 1;

    public static final int INTAKE_INNER_ACTUATOR_A = 2;
    public static final int INTAKE_INNER_ACTUATOR_B = 3;

    // lift
    public static final int LIFT_0 = 0;
    public static final int LIFT_1 = 0;
    public static final int LIFT_2 = 0;
    public static final int LIFT_3 = 0;
    public static final int LIFT_4 = 0;

    public static final int LIFT_SHIFTER_A = 4;
    public static final int LIFT_SHIFTER_B = 5;

    // beltbar
    public static final int BELTBAR_0 = 0;
    public static final int BELTBAR_1 = 0;

    // robot constants

    public static final double DRIVE_ENCODER_COUNTS_PER_REV = 4500;

    public static final double WHEEL_DIAMETER_INCHES = 4.0625;
    public static final double WHEELBASE_INCHES = 28.0;

    public static final ArrayList<Waypoint> figureEightPath = new ArrayList<>();
    public static final ArrayList<Waypoint> sigmoidPath = new ArrayList<>();

    static {
        figureEightPath.add(new Waypoint(Length.fromInches(32.470), 1.1821));
        figureEightPath.add(new Waypoint(Length.fromInches(32.470), -1.1821));
        figureEightPath.add(new Waypoint(Length.fromInches(22.140), -1.1571));
        figureEightPath.add(new Waypoint(Length.fromInches(22.140), -1.1571));
        figureEightPath.add(new Waypoint(Length.fromInches(28.367), -1.3498));
        figureEightPath.add(new Waypoint(Length.fromInches(28.367), 1.3498));
        for (Waypoint waypoint : figureEightPath) {
           // waypoint.setRadius(Robot.drive.inchesToEncoderCounts(waypoint.getRadius()));
        }

        sigmoidPath.add(new Waypoint(Length.fromInches(39.497), 1.1467));
        sigmoidPath.add(new Waypoint(Length.fromInches(37.191), -1.202));
        for (Waypoint waypoint : sigmoidPath) {
         //   waypoint.setRadius(Robot.drive.inchesToEncoderCounts(waypoint.getRadius()));
        }
    }

}
