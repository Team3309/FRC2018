package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.commands.InterruptAll;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetHighGear;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetLowGear;
import org.usfirst.frc.team3309.lib.input.InputXbox;

/*
 * <p>Class for defining controllers
 *
 * @author Chase Blagden
 */
public class OI {

    public static InputXbox driverRemote = new InputXbox(0);
    public static InputXbox operatorRemote = new InputXbox(1);

    public OI() {
        driverRemote.leftBumper.whenPressed(new DriveSetLowGear());
        driverRemote.leftBumper.whenReleased(new DriveSetHighGear());
        driverRemote.backButton.whenPressed(new InterruptAll()); //kills all running commands, essentially a soft-estop.
    }

}
