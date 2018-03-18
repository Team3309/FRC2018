package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.robot.Robot;

//Intended to only be run once, in robotInit();
public class LiftFindZero extends Command {
    private double start = Double.NaN;
    private final double DESIRED_TIME_ELAPSED = 0.3;
    private boolean hasStarted = false;

    private static boolean isZeroed = false;

    public LiftFindZero() {
        setRunWhenDisabled(true);
    }

    @Override
    protected void execute() {
        if (Robot.lift.isAtBottom()) {
            if (!hasStarted) {
                start = Timer.getFPGATimestamp();
                hasStarted = true;
            } else {
                double timeElapsed = Timer.getFPGATimestamp() - start;
                if (timeElapsed > DESIRED_TIME_ELAPSED) {
                    Robot.lift.resetToBottom();
                    isZeroed = true;
                }
            }
        } else {
            hasStarted = false;
        }
    }

    @Override
    protected boolean isFinished() {
        return isZeroed;
    }

    @Override
    protected void end() {
        super.end();
        hasStarted = false;
    }

    public static boolean isZeroed() {
        return isZeroed;
    }

}
