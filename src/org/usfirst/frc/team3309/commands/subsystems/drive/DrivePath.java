package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.controllers.drive.BiArcController;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveSignal;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveState;
import org.usfirst.frc.team3309.lib.controllers.helpers.Waypoint;
import org.usfirst.frc.team3309.robot.Constants;
import org.usfirst.frc.team3309.robot.Robot;

import java.util.ArrayList;

@Deprecated
public class DrivePath extends Command {

    private BiArcController biArcController;
    private double start;
    private boolean isInitialized = false;
    private final double TIMEOUT_SEC;
    private boolean backwards = false;

    public DrivePath(ArrayList<Waypoint> path, boolean backwards, double timeoutSec) {
        requires(Robot.drive);
        for (Waypoint waypoint : path) {
            waypoint.setRadius(Robot.drive.inchesToEncoderCounts(waypoint.getRadius()));
        }
        this.backwards = backwards;
        biArcController = new BiArcController(path,
                Robot.drive.inchesToEncoderCounts(Constants.WHEELBASE_INCHES.toInches()), 26000);
        this.TIMEOUT_SEC = timeoutSec;
    }

    public DrivePath(ArrayList<Waypoint> path, boolean backwards) {
        this(path, backwards, Double.POSITIVE_INFINITY);
    }

    public DrivePath(ArrayList<Waypoint> path){
        this(path,false, Double.POSITIVE_INFINITY);
    }

    @Override
    protected void initialize() {
        Robot.drive.setHighGear();
        Robot.drive.changeToBrakeMode();
        Robot.drive.changeToVelocityMode();
        isInitialized = true;
    }

    @Override
    protected void execute() {
        if  (!isInitialized) {
            initialize();
        }
        DriveState driveState = new DriveState(Robot.drive.getEncoderPos(), Math.toRadians(Robot.drive.getAngPos()));
        DriveSignal driveSignal = biArcController.update(driveState);
        if (backwards) {
            Robot.drive.setLeftRight(-driveSignal.getLeftMotor(), -driveSignal.getRightMotor());
        } else {
            Robot.drive.setLeftRight(driveSignal.getLeftMotor(), driveSignal.getRightMotor());
        }
    }

    @Override
    protected boolean isFinished() {
        return biArcController.isFinished();
    }

    @Override
    public void end() {
        start = Double.POSITIVE_INFINITY;
        isInitialized = false;
    }

}
