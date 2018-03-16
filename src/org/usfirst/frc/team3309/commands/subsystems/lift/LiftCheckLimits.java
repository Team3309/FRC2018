package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftCheckLimits extends Command {

    private double start = Double.POSITIVE_INFINITY;
    private final double DESIRED_TIME_ELUDED = 0.3;
    private boolean hasStarted = false;

    private boolean isZeroed = false;

    @Override
    protected void execute() {
        if (Robot.lift.isAtBottom() && !isZeroed) {
            if (!hasStarted) {
                start = Timer.getFPGATimestamp();
                hasStarted = true;
            } else {
                double timeEluded = Timer.getFPGATimestamp() - start;
                if (timeEluded > DESIRED_TIME_ELUDED) {
                    Robot.lift.resetToBottom();
                    hasStarted = false;
                    isZeroed = true;
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
