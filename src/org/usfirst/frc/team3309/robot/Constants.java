package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.lib.Length;
import org.usfirst.frc.team3309.lib.controllers.helpers.Waypoint;

import java.util.ArrayList;

public class Constants {

    // drive
    public static final int DRIVE_RIGHT_0_ID = 3;
    public static final int DRIVE_RIGHT_1_ID = 2;
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

    public static final int LIFT_TOP_LIMIT_SWITCH = 0;
    public static final int LIFT_BOTTOM_LIMIT_SWITCH = 1;

    // beltbar
    public static final int BELTBAR_0 = 0;
    public static final int BELTBAR_1 = 0;

    // shooter
    public static final int SHOOTER_0 = 0;

    // robot constants

    public static final double DRIVE_ENCODER_COUNTS_PER_REV = 1.5*4096.0*(150.0/34.0);

    public static final Length WHEEL_DIAMETER_INCHES = Length.fromInches(4.0625);
    public static final Length WHEELBASE_INCHES = Length.fromInches(28.0);

    public static final Length BELTBAR_ARM_LENGTH = Length.fromInches(9);

    public static final ArrayList<Waypoint> figureEightPath = new ArrayList<>();
    public static final ArrayList<Waypoint> sigmoidPath = new ArrayList<>();
    public static final ArrayList<Waypoint> circularPath = new ArrayList<>();
    public static final ArrayList<Waypoint> semiCircular = new ArrayList<>();
    public static final ArrayList<Waypoint> toSwitchPath = new ArrayList<>();


    static {
        figureEightPath.add(new Waypoint(32.470, 1.1821));
        figureEightPath.add(new Waypoint(32.470, -1.1821));
        figureEightPath.add(new Waypoint(22.140, -1.1571));
        figureEightPath.add(new Waypoint(22.140, -1.1571));
        figureEightPath.add(new Waypoint(28.367, -1.3498));
        figureEightPath.add(new Waypoint(28.367, 1.3498));

        sigmoidPath.add(new Waypoint(39.497, 1.1467));
        sigmoidPath.add(new Waypoint(37.191, -1.202));

        circularPath.add(new Waypoint(27.48, 1.622));
        circularPath.add(new Waypoint(29.5, 1.552));
        circularPath.add(new Waypoint(29.2969, 0.8153));
        circularPath.add(new Waypoint(28.6254, 0.8323));
        circularPath.add(new Waypoint(27.3257, 0.79898));
        circularPath.add(new Waypoint(30.2656, 0.72836));

        semiCircular.add(new Waypoint(25.169, 1.6019));
        semiCircular.add(new Waypoint(25.478, 1.5897));

        toSwitchPath.add(new Waypoint(-87.135, 0.9282));
        toSwitchPath.add(new Waypoint(-43.654, 1.5699));
        toSwitchPath.add(new Waypoint(44.6938, -1.3548));
        toSwitchPath.add(new Waypoint(55.8847, -1.1433));
    }

}
