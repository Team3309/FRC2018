package org.usfirst.frc.team3309.auto;

import org.usfirst.frc.team3309.auto.routines.NoAutoRoutine;
import org.usfirst.frc.team3309.auto.routines.TestRoutine;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Chase.Blagden
 *         <p>
 *         Class for interfacing between Robot class and selecting and running
 *         auto modes.
 */
public class AutoModeExecutor {

	private static SendableChooser<AutoRoutine> autos = new SendableChooser<>();
	private static boolean isUsingFile = false;

	/*
	 * <p>Loops through and puts autos on dashboard for selection
	 */
	public static void displayAutos() {
		if (isUsingFile) {
			FileReaderAutoRoutine.displayAutos();
		} else {
			autos.addDefault("NoActionRoutine", new NoAutoRoutine());
			autos.addObject("TestRoutine", new TestRoutine());
			SmartDashboard.putData("Autos: ", autos);
		}
	}

	/*
	 * @returns AutoRoutine that has been selected by user
	 */
	public static AutoRoutine getAutoSelected() {
		if (isUsingFile) {
			return new FileReaderAutoRoutine();
		}
		return autos.getSelected();
	}

	/*
	 * @params boolean usingFile - specifies whether file will be read for auto
	 * or hard code
	 */
	public void setIsUsingFile(boolean usingFile) {
		isUsingFile = usingFile;
	}

}
