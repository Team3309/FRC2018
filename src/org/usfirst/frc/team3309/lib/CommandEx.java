package org.usfirst.frc.team3309.lib;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class CommandEx extends Command {

    @Override
    public void initialize(){
        System.out.println(Timer.getFPGATimestamp() + " ---- " + getName() + " - initialized");
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    public void end() {
        System.out.println(Timer.getFPGATimestamp() + " ---- " + getName() + " - end");
    }

}
