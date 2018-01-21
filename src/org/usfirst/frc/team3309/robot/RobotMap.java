package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.commands.subsystems.AssemblyPosition;
import org.usfirst.frc.team3309.lib.controllers.drive.Waypoint;

import java.util.ArrayList;

public class RobotMap {

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

    public static final double WHEEL_DIAMETER_INCHES = 4.0625;
    public static final double WHEELBASE_INCHES = 28.0;

    public static final ArrayList<Waypoint> figureEightPath = new ArrayList<>();
    public static final ArrayList<Waypoint> sigmoidPath = new ArrayList<>();

    static {
        figureEightPath.add(new Waypoint(32.470, 1.1821));
        figureEightPath.add(new Waypoint(32.470, -1.1821));
        figureEightPath.add(new Waypoint(22.140, -1.1571));
        figureEightPath.add(new Waypoint(22.140, -1.1571));
        figureEightPath.add(new Waypoint(28.367, -1.3498));
        figureEightPath.add(new Waypoint(28.367, 1.3498));
        for (Waypoint waypoint : figureEightPath) {
            waypoint.setRadius(Robot.drive.inchesToEncoderCounts(waypoint.getRadius()));
        }

        sigmoidPath.add(new Waypoint(39.497, 1.1467));
        sigmoidPath.add(new Waypoint(37.191, -1.202));
        for (Waypoint waypoint : sigmoidPath) {
            waypoint.setRadius(Robot.drive.inchesToEncoderCounts(waypoint.getRadius()));
        }
    }

    public static final AssemblyPosition scaleDown = new AssemblyPosition(0, 0);
    public static final AssemblyPosition scaleUp = new AssemblyPosition(0, 0);
    public static final AssemblyPosition switchDown = new AssemblyPosition(0, 0);
    public static final AssemblyPosition switchUp = new AssemblyPosition(0, 0);
    public static final AssemblyPosition tuckInCube = new AssemblyPosition(0, 0);
    public static final AssemblyPosition holdCube = new AssemblyPosition(0, 0);

}
