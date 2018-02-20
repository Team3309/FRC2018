package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.commands.InterruptAll;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsClamp;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsOpen;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveForward;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetHighGear;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetLowGear;
import org.usfirst.frc.team3309.commands.subsystems.falcondoors.FalconDoorsDeploy;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftShiftToClimbMode;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterBack;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterForward;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterShoot;
import org.usfirst.frc.team3309.lib.input.InputXbox;
import org.usfirst.frc.team3309.lib.math.Length;

/*
 * <p>Class for defining controllers
 *
 * @author Chase Blagden
 */
public class OI {

    public static InputXbox driverRemote = new InputXbox(0);
    public static InputXbox operatorRemote = new InputXbox(1);

    OI() {
        operatorRemote.setDeadZone(0.03);
        driverRemote.setDeadZone(0.03);

        driverRemote.leftBumper.whenPressed(new DriveSetLowGear());
        driverRemote.leftBumper.whenReleased(new DriveSetHighGear());

        driverRemote.backButton.whenPressed(new InterruptAll());
        operatorRemote.backButton.whenPressed(new InterruptAll());

        operatorRemote.buttonA.whenPressed(new FalconDoorsDeploy());
        operatorRemote.buttonX.whenPressed(new ArmsClamp());
        operatorRemote.buttonY.whenPressed(new ArmsOpen());
     //   operatorRemote.rightBumper.whenPressed(new SetClimbMode());
         operatorRemote.rightBumper.whenPressed(new LiftShiftToClimbMode());

     //   operatorRemote.leftBumper.whileHeld(new LiftSet(1));

        operatorRemote.leftBumper.whenPressed(new ShooterForward());
        operatorRemote.leftStick.whenReleased(new ShooterBack());

        operatorRemote.buttonB.whenPressed(new DriveForward(Length.fromInches(36)));
    }

}
