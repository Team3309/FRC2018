package org.usfirst.frc.team3309.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.robot.Constants;
import org.usfirst.frc.team3309.robot.Robot;

public class FalconDoors extends Subsystem {

    private Solenoid shifter = new Solenoid(Constants.FALCONDOORS_SOLENOID);

    @Override
    protected void initDefaultCommand() {
    }

    public void setDown() {
        shifter.set(true);
    }

    public void setUp() {
        shifter.set(false);
    }

}
