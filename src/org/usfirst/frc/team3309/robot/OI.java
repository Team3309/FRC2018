package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.commands.InterruptAll;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsClamp;
import org.usfirst.frc.team3309.commands.subsystems.arms.ArmsOpen;
import org.usfirst.frc.team3309.commands.subsystems.beltbar.BeltBarMoveToPos;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetHighGear;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetLowGear;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftElevate;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftShiftToClimbMode;
import org.usfirst.frc.team3309.commands.subsystems.shooter.ShooterForward;
import org.usfirst.frc.team3309.lib.input.InputXbox;

/*
 * <p>Class for defining controllers
 *
 * @author Chase Blagden
 */
public class OI {

    public static InputXbox driverRemote = new InputXbox(0);
    public static InputXbox operatorRemote = new InputXbox(1);

    OI() {

        driverRemote.leftBumper.whenPressed(new DriveSetLowGear());
        driverRemote.leftBumper.whenReleased(new DriveSetHighGear());

        driverRemote.backButton.whenPressed(new InterruptAll());
        operatorRemote.backButton.whenPressed(new InterruptAll());

        operatorRemote.buttonA.whenPressed(new ArmsClamp());
        operatorRemote.buttonB.whenPressed(new ArmsOpen());

        operatorRemote.leftBumper.whenPressed(new ShooterForward());
        operatorRemote.rightBumper.whenPressed(new LiftShiftToClimbMode());

        operatorRemote.buttonX.whenPressed(new LiftElevate(AssemblyLocation.GROUND.getElevatorPosition()));
        operatorRemote.buttonY.whenPressed(new LiftElevate(AssemblyLocation.SWITCH.getElevatorPosition()));
        operatorRemote.dPad.left.whenPressed(new LiftElevate(AssemblyLocation.SCALE_DOWN.getElevatorPosition()));
        operatorRemote.dPad.right.whenPressed(new LiftElevate(AssemblyLocation.SCALE_UP.getElevatorPosition()));

        operatorRemote.dPad.down.whenPressed(new BeltBarMoveToPos(AssemblyLocation.SWITCH.getBeltBarPosition())); // 2500
        operatorRemote.startButton.whenPressed(new BeltBarMoveToPos(AssemblyLocation.SCALE_DOWN.getBeltBarPosition())); // 3500
    }


}

