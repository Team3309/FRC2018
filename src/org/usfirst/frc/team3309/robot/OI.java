package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.MoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.PrepareForClimb;
import org.usfirst.frc.team3309.commands.subsystems.SetClimbMode;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsClamp;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsOpen;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetHighGear;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetLowGear;
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

    public static JoystickButton driverShiftButton = new JoystickButton(driverRemoteLeft, Constants.JOYSTICK_TRIGGER_BUTTON);s

    OI() {
        /* =====DRIVER===== */
        driverShiftButton.whenPressed(new DriveSetLowGear());
        driverShiftButton.whenReleased(new DriveSetHighGear());

        /* =====OPERATOR===== */
        operatorRemote.buttonA.whenPressed(new ArmsClamp());
        operatorRemote.buttonB.whenPressed(new ArmsOpen());

        operatorRemote.startButton.whenPressed(new PrepareForClimb());
        operatorRemote.rightStick.whenPressed(new SetClimbMode());

        operatorRemote.buttonX.whenPressed(new MoveAssembly(AssemblyLocation.INTAKE, true));
        operatorRemote.buttonY.whenPressed(new MoveAssembly(AssemblyLocation.BOTTOM, true));
        operatorRemote.dPad.down.whenPressed(new MoveAssembly(AssemblyLocation.SWITCH, true));
        operatorRemote.dPad.right.whenPressed(new MoveAssembly(AssemblyLocation.SCALE_DOWN, true));
        operatorRemote.dPad.up.whenPressed(new MoveAssembly(AssemblyLocation.SCALE_MIDDLE, true));
        operatorRemote.dPad.left.whenPressed(new MoveAssembly(AssemblyLocation.SCALE_UP, true));
        operatorRemote.rightBumper.whenPressed(new MoveAssembly(AssemblyLocation.EXCHANGE_ZONE, true));

    }

}