package org.usfirst.frc.team3309.robot;

import org.usfirst.frc.team3309.commands.subsystems.MoveAssembly;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveSetLowGear;
import org.usfirst.frc.team3309.driverstation.Controls;

public class OI {

    public OI() {
        Controls.driverRemote.leftBumper.whileHeld(new DriveSetLowGear());
        Controls.operatorRemote.buttonA.whileHeld(new MoveAssembly(RobotMap.holdCube));
        Controls.operatorRemote.buttonB.whileHeld(new MoveAssembly(RobotMap.tuckInCube));
    }

}
