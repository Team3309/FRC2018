package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetHighGear;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetLowGear;
import org.usfirst.frc.team3309.lib.InputXbox;

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
        // OI.operatorRemote.buttonA.whileHeld(new MoveAssembly(Constants.holdCube));
        // OI.operatorRemote.buttonB.whileHeld(new MoveAssembly(Constants.tuckInCube));
        // OI.operatorRemote.buttonA.whenPressed(new DrivePath(Constants.semiCircularPath));
    }

}
