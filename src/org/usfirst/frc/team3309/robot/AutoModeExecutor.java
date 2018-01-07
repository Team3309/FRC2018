package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.json.JSONArray;
import lib.json.JSONObject;
import org.usfirst.frc.team3309.commands.autos.DriveForwardAuto;
import org.usfirst.frc.team3309.commands.autos.DrivePathAuto;
import org.usfirst.frc.team3309.commands.autos.NoActionsAuto;
import org.usfirst.frc.team3309.commands.autos.StupidAutoTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
 * TODO: implement parser for JSON files on roborio
 */
public class AutoModeExecutor {

    private static SendableChooser<Command> autos = new SendableChooser<>();

    private static final boolean isUsingFile = false;
    private static final File[] autoFiles = new File("/home/lvuser/Autos/")
            .listFiles();

    public static void displayAutos() {
        autos.addDefault("No Action", new NoActionsAuto());
        if (!isUsingFile) {
            autos.addObject("DriveForwardAuto", new DriveForwardAuto());
            autos.addObject("PurePursuitAuto", new DrivePathAuto());
            autos.addObject("StupidCircleAuto", new StupidAutoTest());
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
        FileInputStream fis = new FileInputStream(autoFile);
        String json = "";
        int read = 0;
        while ((read = fis.read()) != -1) {
            json += (char) read;
        }
        JSONObject autoData = new JSONObject(json);
        JSONArray waypoints = autoData.getJSONArray("waypoints");
        return null;
    }

}
