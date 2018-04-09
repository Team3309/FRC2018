package org.usfirst.frc.team3309.lib.sensors;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch extends Counter {

    public LimitSwitch(int port){
        super(new DigitalInput(port));
    }

    public boolean isSwitchSet() {
        return this.get() > 0;
    }

}
