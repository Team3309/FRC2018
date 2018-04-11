package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.commands.autos.*;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTurn;
import org.usfirst.frc.team3309.commands.subsystems.drive.FollowPathCommand;
import org.usfirst.frc.team4322.math.Path;
import org.usfirst.frc.team4322.math.PathBuilder;

import java.util.ArrayList;

public class AutoModeExecutor {

    private static SendableChooser<Command> autos = new SendableChooser<>();

    public static void displayAutos() {

        autos.addDefault("No Action", new NoActionAuto());
        autos.addObject("AutoLineAuto", new AutoLineAuto());
        autos.addObject("MiddleSwitchAuto", new CurvyToSwitchAuto());

        autos.addObject("RightScaleAuto", new ScaleOnlyAuto(true, false));
        autos.addObject("LeftScaleAuto", new ScaleOnlyAuto(false, false));

        autos.addObject("RightScaleAnd(Switch)Auto", new ScaleOnlyAuto(true, true));
        autos.addObject("LeftScaleAnd(Switch)Auto", new ScaleOnlyAuto(false, true));

        autos.addObject("RightScaleThreeCubeAuto", new ScaleThreeCubeAuto(true));

        autos.addObject("LeftSwitchAuto", new SideSwitchAuto(false));
        autos.addObject("RightSwitchAuto", new SideSwitchAuto(true));

        ArrayList<PathBuilder.Waypoint> waypoints = new ArrayList<>();
        waypoints.add(new PathBuilder.Waypoint(0.0,0.0,0.0,0.0));
//        waypoints.add(new PathBuilder.Waypoint(72.0,0.0,0.0,200.0));
//        waypoints.add(new PathBuilder.Waypoint(196,0.0,0.0,100.0));
        waypoints.add(new PathBuilder.Waypoint(72,-32,0,36.0));

        Path path = PathBuilder.INSTANCE.buildPathFromWaypoints(waypoints);
        autos.addObject("TurnTest", new DriveTurn(90, Double.POSITIVE_INFINITY, true));
        autos.addObject("Drive Forward Path Test",new FollowPathCommand(path,false));
        SmartDashboard.putData("Autos: ", autos);
    }

    public static Command getAutoSelected() {
        return autos.getSelected();
    }


}
