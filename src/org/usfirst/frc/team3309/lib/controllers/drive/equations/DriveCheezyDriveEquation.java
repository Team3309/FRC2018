package org.usfirst.frc.team3309.lib.controllers.drive.equations;

import org.usfirst.frc.team3309.lib.LibMath;
import org.usfirst.frc.team3309.lib.controllers.Controller3;
import org.usfirst.frc.team3309.lib.controllers.drive.DriveSignal;

/**
 * <p>
 * Drive controller invented by Team 254,
 * https://github.com/Team254/FRC-2017-Public
 * /blob/master/src/com/team254/org.usfirst.frc.team3309.lib/util/CheesyDriveHelper.java
 * <p>
 * <p>
 * Helper class to implement "Cheesy Drive". "Cheesy Drive" simply means that
 * the "turning" stick controls the curvature of the robot's path rather than
 * its rate of heading change. This helps make the robot more controllable at
 * high speeds. Also handles the robot's quick turn functionality - "quick turn"
 * overrides constant-curvature turning for turn-in-place maneuvers.
 */
public class DriveCheezyDriveEquation extends Controller3<DriveSignal, Double, Double, Boolean> {

    private double oldWheel, quickStopAccumulator;

    private double throttleDeadband = 0.04;
    private double wheelDeadband = 0.04;

    public DriveCheezyDriveEquation(double throttleDeadband, double turnDeadband) {
        this.throttleDeadband = throttleDeadband;
        this.wheelDeadband = turnDeadband;
    }

    public DriveCheezyDriveEquation() {
    }

    @Override
    public DriveSignal update(Double throttle, Double turn, Boolean isQuickTurn) {
        DriveSignal driveSignal;
        boolean isHighGear = true;
        double wheelNonLinearity;

        turn = LibMath.handleDeadband(turn, wheelDeadband);
        throttle = LibMath.handleDeadband(throttle, throttleDeadband);

        double negInertia = turn - oldWheel;
        oldWheel = turn;

        if (isHighGear) {
            wheelNonLinearity = 0.6;
            // Apply a sin function that's scaled to make it feel better.
            turn = Math.sin(Math.PI / 2.0 * wheelNonLinearity * turn)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
            turn = Math.sin(Math.PI / 2.0 * wheelNonLinearity * turn)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
        } else {
            wheelNonLinearity = 0.5;
            // Apply a sin function that's scaled to make it feel better.
            turn = Math.sin(Math.PI / 2.0 * wheelNonLinearity * turn)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
            turn = Math.sin(Math.PI / 2.0 * wheelNonLinearity * turn)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
            turn = Math.sin(Math.PI / 2.0 * wheelNonLinearity * turn)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
        }

        double leftPwm, rightPwm, overPower;
        double sensitivity = 0.3; // 0.3

        double angularPower;
        double linearPower;

        // Negative inertia!
        double negInertiaAccumulator = 0.0;
        double negInertiaScalar = 5;
        if (isHighGear) {

            negInertiaScalar = 5.0;
        } else {
            /*
             * if (wheel * negInertia > 0) { negInertiaScalar = 1.75; } else {
             * if (Math.abs(wheel) > 0.65) { negInertiaScalar = 4.0; } else {
             * negInertiaScalar = 3.0; } } sensitivity = .6;
             */
        }
        double negInertiaPower = negInertia * negInertiaScalar;
        negInertiaAccumulator += negInertiaPower;

        turn = turn + negInertiaAccumulator;
        if (negInertiaAccumulator > 1) {
            negInertiaAccumulator -= 1;
        } else if (negInertiaAccumulator < -1) {
            negInertiaAccumulator += 1;
        } else {
            negInertiaAccumulator = 0;
        }
        linearPower = throttle;

        if (throttle > .4) {
            sensitivity = .15;
        } else {
            sensitivity = .3;
        }
        // Quickturn!
        if (isQuickTurn) {
            if (Math.abs(linearPower) < 0.2) {
                double alpha = 0.05;
                quickStopAccumulator = (1 - alpha) * quickStopAccumulator
                        + alpha * limit(turn, 1.0) * 5;
            }
            overPower = 1.0;
            if (isHighGear) {
                sensitivity = .455;
            } else {
                sensitivity = .455;
            }
            angularPower = turn * sensitivity;
            if (turn == 1 || turn == -1) {
                angularPower = turn;
            }
        } else {
            overPower = 0.0;
            angularPower = Math.abs(throttle) * turn * sensitivity
                    - quickStopAccumulator;
            if (quickStopAccumulator > 1) {
                quickStopAccumulator -= 1;
            } else if (quickStopAccumulator < -1) {
                quickStopAccumulator += 1;
            } else {
                quickStopAccumulator = 0.0;
            }
        }

        rightPwm = leftPwm = linearPower;
        leftPwm -= angularPower;
        rightPwm += angularPower;

        if (leftPwm > 1.0) {
            rightPwm -= overPower * (leftPwm - 1.0);
            leftPwm = 1.0;
        } else if (rightPwm > 1.0) {
            leftPwm -= overPower * (rightPwm - 1.0);
            rightPwm = 1.0;
        } else if (leftPwm < -1.0) {
            rightPwm += overPower * (-1.0 - leftPwm);
            leftPwm = -1.0;
        } else if (rightPwm < -1.0) {
            leftPwm += overPower * (-1.0 - rightPwm);
            rightPwm = -1.0;
        }
        driveSignal = new DriveSignal(leftPwm, rightPwm);
        return driveSignal;
    }

    private static double limit(double v, double limit) {
        return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
    }

}
