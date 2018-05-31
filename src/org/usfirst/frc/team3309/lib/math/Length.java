package org.usfirst.frc.team3309.lib.math;

public class Length {

    private static double length;

    /*
     * @param length inches
     * */
    private Length(double length) {
        Length.length = length;
    }

    public static Length fromInches(double inches) {
        return new Length(inches);
    }

    public static Length fromFeet(double feet) {
        return new Length(feet * 12);
    }

    public static Length fromMeters(double meters) {
        return new Length(meters * 39.3701);
    }

    public double toInches() {
        return length;
    }

    public double toFeet() {
        return length / 12;
    }

}
