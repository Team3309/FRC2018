package org.usfirst.frc.team3309.commands.subsystems.lift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.CommandEx;
import org.usfirst.frc.team3309.robot.Robot;

//Intended to only be run once, in robotInit();
public class LiftFindZero extends CommandEx {
    private double start = Double.NaN;
    private final double DESIRED_TIME_ELAPSED = 0.3;
    private boolean hasStarted = false;

    private static boolean isZeroed = false;

    public LiftFindZero() {
        setRunWhenDisabled(true);
    }

    @Override
    public void initialize() {
        super.initialize();
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

    @Override
    public void end() {
        super.end();
        hasStarted = false;
    }

    public static boolean isZeroed() {
        return isZeroed;
    }

}
