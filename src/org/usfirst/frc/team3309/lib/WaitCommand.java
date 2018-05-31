package org.usfirst.frc.team3309.lib;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class WaitCommand extends Command {

    private double start;
    private boolean isInitialized = false;
    private double sec;

    public WaitCommand(double sec) {
        this.sec = sec;
    }

    @Override
    public void initialize() {
        isInitialized = true;
        start = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        if (!isInitialized) {
            initialize();
        }
    }

    @Override
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - start > sec;
    }


}
