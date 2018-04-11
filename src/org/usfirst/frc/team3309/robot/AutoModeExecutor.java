package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.commands.autos.*;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveTurn;

public class AutoModeExecutor {

    private static SendableChooser<Command> autos = new SendableChooser<>();

    public static void displayAutos() {

        autos.addDefault("No Action", new NoActionAuto());
        autos.addObject("AutoLineAuto", new AutoLineAuto());
        autos.addObject("MiddleSwitchAuto", new MiddleSwitchAuto());

        autos.addObject("RightScaleAuto", new ScaleOnlyAuto(true, false));

        autos.addObject("RightScaleAnd(Switch)Auto", new ScaleOnlyAuto(true, true));

        autos.addObject("LeftSwitchAuto", new SideSwitchAuto(false));
        autos.addObject("RightSwitchAuto", new SideSwitchAuto(true));

        autos.addObject("StayRightAuto", new StayRightAuto());

        SmartDashboard.putData("Autos: ", autos);
    }

    public static Command getAutoSelected() {
        return autos.getSelected();
    }


}
