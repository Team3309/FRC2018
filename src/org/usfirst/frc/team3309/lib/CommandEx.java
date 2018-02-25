package org.usfirst.frc.team3309.lib;

import edu.wpi.first.wpilibj.command.Command;

public class CommandEx extends Command {

    private boolean isInitializedEx = false;

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    public void end() {
        isInitializedEx = false;
    }

}
