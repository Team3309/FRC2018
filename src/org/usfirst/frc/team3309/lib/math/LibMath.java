package org.usfirst.frc.team3309.lib.math;

public class LibMath {

    public static double handleDeadband(double val, double lim) {
        return Math.abs(val) < Math.abs(lim) ? 0.0 : val;
    }

    public static boolean isInThreshold(double val, double threshold) {
        return Math.abs(val) < Math.abs(threshold);
    }

    public static boolean isWithin(double val, double a, double b) {
        if (a < b) {
            return val > a && val < b;
        }
        return val < a && val > b;
    }


}
