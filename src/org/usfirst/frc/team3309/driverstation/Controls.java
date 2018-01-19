package org.usfirst.frc.team3309.driverstation;

import edu.wpi.first.wpilibj.Joystick;

/*
 * <p>Class for defining controllers
 *
 * @author Chase Blagden
 */
public class Controls {

    public static XboxController driverRemote = new XboxController();
    public static XboxController operatorRemote = new XboxController(1);

    public static Joystick driverRemoteY = new Joystick(3);
    public static Joystick driverRemoteX = new Joystick(4);

}
