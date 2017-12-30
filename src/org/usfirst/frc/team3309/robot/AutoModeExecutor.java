package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.commands.autos.NoActionsAuto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/*
 * TODO: implement parser for JSON files on roborio
 * */
public class AutoModeExecutor {
	
	private static SendableChooser<Command> autos = new SendableChooser<>(); 
	
	public static void displayAutos() {
		autos.addDefault("No Action", new NoActionsAuto());
	}
	
	public static Command getAutoSelected() {
		return autos.getSelected();
	}

}
