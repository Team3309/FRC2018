package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.commands.autos.NoActionsAuto;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveForward;
import org.usfirst.frc.team3309.commands.subsystems.drive.DrivePath;
import org.usfirst.frc.team3309.lib.Length;
import org.usfirst.frc.team3309.lib.controllers.helpers.Waypoint;

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
        autos.addObject("DriveForwardAuto", new DriveForward(Length.fromInches(30), 0));
        autos.addObject("Figure Eight Path", new DrivePath(Constants.figureEightPath));
        autos.addObject("Sigmoid Path", new DrivePath(Constants.sigmoidPath));
        autos.addObject("Switch Path", new DrivePath(Constants.toSwitchPath));
        autos.addObject("Circular Path", new DrivePath(Constants.circularPath));
        autos.addObject("SemiCircular", new DrivePath(Constants.semiCircular));
        if (isUsingFile) {
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
    private static CommandGroup buildAuto(File autoFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(autoFile));
        String line = reader.readLine();
        ArrayList<Waypoint> path = new ArrayList<>();
        CommandGroup autoGroup = new CommandGroup();
        while (line != null) {
            String[] values = line.split(",");
            String commandType = values[0];
            if (commandType == "ARC" ) {
                double radius = Double.parseDouble(values[1]);
                double angle = Double.parseDouble(values[2]);
                path.add(new Waypoint(radius, angle));
            }
        }
        autoGroup.addSequential(new DrivePath(path));
        reader.close();
        return autoGroup;
    }

}
