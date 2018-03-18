package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.commands.autos.AutoLineAuto;
import org.usfirst.frc.team3309.commands.autos.CurvyToSwitchAuto;
import org.usfirst.frc.team3309.commands.autos.NoActionAuto;
import org.usfirst.frc.team3309.commands.autos.ScaleOnlyAuto;

public class AutoModeExecutor {

    private static SendableChooser<Command> autos = new SendableChooser<>();

    public static void displayAutos() {

        autos.addObject("No Action", new NoActionAuto());
        autos.addDefault("CurvyToSwitchAuto", new CurvyToSwitchAuto());
        autos.addObject("ScaleOnlyAuto", new ScaleOnlyAuto());
        autos.addObject("AutoLineAuto", new AutoLineAuto());

        SmartDashboard.putData("Autos: ", autos);
    }

    public static Command getAutoSelected() {
        return autos.getSelected();
    }


}
