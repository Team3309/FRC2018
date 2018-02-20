package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.commands.autos.AutoLineAuto;
import org.usfirst.frc.team3309.commands.autos.NoActionAuto;
import org.usfirst.frc.team3309.commands.autos.SwitchForwardAuto;
import org.usfirst.frc.team3309.commands.autos.TurnToSwitchAuto;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltBarMoveToPos;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveForward;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveForwardVelocity;
import org.usfirst.frc.team3309.commands.subsystems.drive.DrivePath;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTurn;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftElevate;
import org.usfirst.frc.team3309.lib.controllers.helpers.Waypoint;
import org.usfirst.frc.team3309.lib.math.Length;

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
        autos.addDefault("No Action", new NoActionAuto());
        autos.addObject("DriveForwardAuto", new DriveForward(Length.fromInches(130)));
     //   autos.addObject("DriveBack", new DriveForward(Length.fromInches(-130)));
        autos.addObject("TurnCWToAngleAuto", new DriveTurn(90));
        autos.addObject("TurnCCWToAngleAuto", new DriveTurn(90));
        autos.addObject("AutoLineAuto", new AutoLineAuto());
        autos.addObject("SwitchForwardAuto", new SwitchForwardAuto());
        autos.addObject("TurnToSwitchAuto", new TurnToSwitchAuto());
        autos.addObject("LiftElevate", new LiftElevate(5000));
        autos.addObject("BeltbarMoveToPos", new BeltBarMoveToPos(0));
        autos.addObject("DriveForwardVelocity", new DriveForwardVelocity(25000));
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
        CommandGroup autoGroup = new CommandGroup();
        while (line != null) {
            String[] values = line.split(",");
            String commandType = values[0];
            switch (commandType) {
                case "ARC":
                    double radius = Double.parseDouble(values[1]);
                    double theta = Double.parseDouble(values[2]);
                    ArrayList<Waypoint> arcPath = new ArrayList<>();
                    arcPath.add(new Waypoint(radius, theta));
                    autoGroup.addSequential(new DrivePath(arcPath));
                case "LINE":
                    double dist = Double.parseDouble(values[1]);
                    autoGroup.addSequential(new DriveForward(Length.fromInches(dist)));
                case "WAIT":
                    double sec = Double.parseDouble(values[1]);
                    autoGroup.addSequential(new WaitCommand(sec));
                case "TURN":
                    double angle = Double.parseDouble(values[1]);
                    autoGroup.addSequential(new DriveTurn(angle));
                case "RUN":
                    try {
                        // TODO("Implement parsing for condition to run command and args to be passed into command")
                        String commandName = values[1];
                        String term = values[2];
                        String args = values[3];
                        Command command = (Command) Class.forName(commandName).cast(Command.class);
                        autoGroup.addSequential(command);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
            }
        }
        reader.close();
        return autoGroup;
    }

}
