package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.controllers.drive.Waypoint;
import org.usfirst.frc.team3309.commands.autos.DriveForwardAuto;
import org.usfirst.frc.team3309.commands.autos.DrivePathAuto;
import org.usfirst.frc.team3309.commands.autos.NoActionsAuto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AutoModeExecutor {

    private static SendableChooser<Command> autos = new SendableChooser<>();

    private static final boolean isUsingFile = false;
    private static final File[] autoFiles = new File("/home/lvuser/Autos/")
            .listFiles();

    private static ArrayList<Waypoint> semiCircularPath = new ArrayList<>();

    public static void displayAutos() {
        semiCircularPath.add(new Waypoint(50, 0.785398));
        semiCircularPath.add(new Waypoint(50, -0.785398));
        autos.addDefault("No Action", new NoActionsAuto());
        autos.addObject("DriveForwardAuto", new DriveForwardAuto());
        if (!isUsingFile) {
            autos.addObject("PurePursuitAuto", new DrivePathAuto(semiCircularPath));
        } else {
            for (File autoFile : autoFiles) {
                Command autoCommand = null;
                try {
                    autoCommand = buildAuto(autoFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (autoCommand != null) {
                    autos.addObject(autoCommand.getName(), autoCommand);
                }
            }
        }
        SmartDashboard.putData("Autos: ", autos);
    }

    public static Command getAutoSelected() {
        return autos.getSelected();
    }

    @SuppressWarnings("resource")
    private static Command buildAuto(File autoFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(autoFile));
        String line = reader.readLine();
        ArrayList<Waypoint> path = new ArrayList<>();
        while (line != null) {
            String[] values = line.split(",");
            double radius = Double.parseDouble(values[0]);
            double angle = Double.parseDouble(values[1]);
            path.add(new Waypoint(radius, angle));
        }
        reader.close();
        return new DrivePathAuto(path);
    }

}
