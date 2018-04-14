package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.commands.subsystems.*;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsClamp;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsMiddle;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsOpen;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltbarStop;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetHighGear;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetLowGear;
import org.usfirst.frc.team3309.commands.subsystems.falcondoors.FalconDoorsDeploy;
import org.usfirst.frc.team3309.lib.input.InputXbox;

/*
 * <p>Class for defining controllers
 *
 * @author Chase Blagden
 */
public class OI {

    public static InputXbox operatorRemote = new InputXbox(2);

    public static Joystick driverRemoteLeft = new Joystick(0);
    public static Joystick driverRemoteRight = new Joystick(1);

    private static JoystickButton driverShiftButton = new JoystickButton(driverRemoteLeft, Constants.JOYSTICK_TRIGGER_BUTTON);

    OI() {

        /* =====DRIVER===== */
        driverShiftButton.whenPressed(new DriveSetLowGear());
        driverShiftButton.whenReleased(new DriveSetHighGear());

        /* =====OPERATOR===== */
        operatorRemote.leftBumper.whenPressed(new ArmsClamp());
        operatorRemote.buttonA.whenPressed(new ArmsMiddle());
        operatorRemote.buttonB.whenPressed(new ArmsOpen());

        operatorRemote.startButton.whenPressed(new PrepareForClimb());
        operatorRemote.rightStick.whenPressed(new SetClimbMode());
        operatorRemote.backButton.whenPressed(new FalconDoorsDeploy());

        operatorRemote.leftStick.whenPressed(new BeltbarStop());

        operatorRemote.buttonX.whenPressed(new FindAndGetCube());
        operatorRemote.buttonY.whenPressed(new MoveAssembly(AssemblyLocation.BOTTOM, true));
        operatorRemote.dPad.down.whenPressed(new MoveAssembly(AssemblyLocation.SWITCH, true));
        operatorRemote.dPad.right.whenPressed(new MoveAssembly(AssemblyLocation.SCALE_DOWN, true));
        operatorRemote.dPad.up.whenPressed(new MoveAssembly(AssemblyLocation.SCALE_MIDDLE, true));
        operatorRemote.dPad.left.whenPressed(new MoveAssembly(AssemblyLocation.SCALE_UP, true));
        operatorRemote.rightBumper.whenPressed(new MoveAssembly(AssemblyLocation.EXCHANGE_ZONE, true));

    }

}