package src.org.usfirst.frc.team3309.lib.math;

public class LibMath {

    public static double handleDeadband(double val, double lim) {
        return Math.abs(val) < Math.abs(lim) ? 0.0 : val;
    }

    public static boolean isInThreshold(double val, double threshold) {
        return Math.abs(val) < Math.abs(threshold);
    }

}
