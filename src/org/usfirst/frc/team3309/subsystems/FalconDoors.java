package org.usfirst.frc.team3309.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3309.robot.Constants;

public class FalconDoors extends Subsystem {

    private Solenoid leftSolenoid = new Solenoid(Constants.FALCONDOORS_LEFT_SOLENOID);
    private Solenoid rightSolenoid = new Solenoid(Constants.FALCONDOORS_RIGHT_SOLENOID);

    @Override
    protected void initDefaultCommand() {
    }

    public void setLeftSolenoidForward() {
        leftSolenoid.set(true);
    }

    public void setRightSolenoidForward() {
        rightSolenoid.set(true);
    }

}
