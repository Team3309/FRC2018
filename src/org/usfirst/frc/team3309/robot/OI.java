package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetHighGear;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetLowGear;
import org.usfirst.frc.team3309.driverstation.Controls;

public class OI {

    public OI() {
        Controls.driverRemote.leftBumper.whenPressed(new DriveSetLowGear());
        Controls.driverRemote.leftBumper.whenReleased(new DriveSetHighGear());
       // Controls.operatorRemote.buttonA.whileHeld(new MoveAssembly(RobotMap.holdCube));
       // Controls.operatorRemote.buttonB.whileHeld(new MoveAssembly(RobotMap.tuckInCube));
       // Controls.operatorRemote.buttonA.whenPressed(new DrivePath(RobotMap.semiCircularPath));
    }

}
