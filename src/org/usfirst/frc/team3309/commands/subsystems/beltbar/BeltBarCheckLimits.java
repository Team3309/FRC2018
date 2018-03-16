package org.usfirst.frc.team3309.commands.subsystems.beltbar;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class BeltBarCheckLimits extends Command {

    private double start = Double.POSITIVE_INFINITY;
    private final double DESIRED_TIME_ELUDED = 0.15;
    private boolean hasStarted = false;

    @Override
    protected void execute() {
        if (Robot.beltBar.isAtTop()) {
            if (!hasStarted) {
                start = Timer.getFPGATimestamp();
                hasStarted = true;
            } else {
                double timeEluded = Timer.getFPGATimestamp() - start;
                if (timeEluded > DESIRED_TIME_ELUDED) {
                    Robot.beltBar.resetToTop();
                    hasStarted = false;
                    start = Double.POSITIVE_INFINITY;
                }
            }
        }

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
