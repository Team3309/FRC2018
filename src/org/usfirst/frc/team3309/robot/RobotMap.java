package org.usfirst.frc.team3309.robot;

import lib.controllers.drive.Waypoint;

import java.util.ArrayList;

public class RobotMap {

    // TALON ID's
    public static final int DRIVE_RIGHT_0_ID = 2;
    public static final int DRIVE_RIGHT_1_ID = 3;
    public static final int DRIVE_RIGHT_2_ID = 4;

    public static final int DRIVE_LEFT_0_ID = 14;
    public static final int DRIVE_LEFT_1_ID = 15;
    public static final int DRIVE_LEFT_2_ID = 16;

    public static final int SHIFTER = 7;

    public static final double WHEEL_DIAMETER_INCHES = 4.0625;
    public static final double WHEELBASE_INCHES = 28.0;

    public static final ArrayList<Waypoint> semiCircularPath = new ArrayList<>();

    static {
        semiCircularPath.add(new Waypoint(51.158, 1.4858));
        semiCircularPath.add(new Waypoint(48.660, 1.5458));
    }

}
