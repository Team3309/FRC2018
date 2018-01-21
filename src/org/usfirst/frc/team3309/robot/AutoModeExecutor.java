package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.commands.autos.NoActionsAuto;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveForward;
import org.usfirst.frc.team3309.commands.subsystems.drive.DrivePath;
import org.usfirst.frc.team3309.lib.controllers.drive.Waypoint;

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


    public static void displayAutos() {
        autos.addDefault("No Action", new NoActionsAuto());
        autos.addObject("DriveForwardAuto", new DriveForward(30));
        if (!isUsingFile) {
            autos.addObject("Figure Eight Path", new DrivePath(RobotMap.figureEightPath));
            autos.addObject("Sigmoid Path", new DrivePath(RobotMap.sigmoidPath));
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
            if (values[0] == "linear") {
                double goal = Double.parseDouble(values[1]);
            } else if (values[0] == "arc") {
                double radius = Double.parseDouble(values[1]);
                double angle = Double.parseDouble(values[2]);
                path.add(new Waypoint(radius, angle));
            } else if (values[0] == "wait") {
                double timeout = Double.parseDouble(values[1]);
            }
        }
        reader.close();
        return new DrivePath(path);
    }

}
