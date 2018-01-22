package org.usfirst.frc.team3309.lib;

public class LibMath {

    public static double handleDeadband(double val, double lim) {
        return Math.abs(val) < Math.abs(lim) ? 0.0 : val;
    }

    public static boolean isInThreshold(double val, double threshold) {
        if (handleDeadband(val, threshold) == 0) {
            return true;
        }
        return false;
    }

}
