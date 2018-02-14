package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.commands.InterruptAll;
import org.usfirst.frc.team3309.commands.subsystems.SetClimbMode;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsClamp;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsIntake;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsOpen;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetHighGear;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetLowGear;
import org.usfirst.frc.team3309.commands.subsystems.falcondoors.FalconDoorsDeploy;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftSet;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterForward;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterShoot;
import org.usfirst.frc.team3309.lib.input.InputXbox;
import sun.util.resources.OpenListResourceBundle;

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

        driverRemote.backButton.whenPressed(new InterruptAll());
        operatorRemote.backButton.whenPressed(new InterruptAll());

        operatorRemote.buttonA.whenPressed(new FalconDoorsDeploy());
        operatorRemote.buttonB.whenPressed(new ArmsIntake());
        operatorRemote.buttonX.whenPressed(new ArmsClamp());
        operatorRemote.buttonY.whenPressed(new ArmsOpen());

        operatorRemote.leftBumper.whenPressed(new ShooterShoot());
    }

}
