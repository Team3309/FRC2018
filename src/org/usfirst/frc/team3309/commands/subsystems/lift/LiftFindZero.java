package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.CommandEx;
import org.usfirst.frc.team3309.robot.Robot;

public class LiftFindZero extends Command {

    private double start = Double.POSITIVE_INFINITY;
    private final double DESIRED_TIME_ELUDED = 0.3;
    private boolean hasStarted = false;

    private static boolean isZeroed = false;

    public LiftFindZero() {
        setRunWhenDisabled(true);
    }

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
                }
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return isZeroed;
    }

    public static boolean isZeroed() {
        return isZeroed;
    }

}
