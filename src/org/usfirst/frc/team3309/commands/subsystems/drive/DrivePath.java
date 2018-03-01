package org.usfirst.frc.team3309.commands.subsystems.drive;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3309.lib.controllers.drive.BiArcController;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveSignal;
import org.usfirst.frc.team3309.lib.controllers.helpers.DriveState;
import org.usfirst.frc.team3309.lib.controllers.helpers.Waypoint;
import org.usfirst.frc.team3309.robot.Constants;
import org.usfirst.frc.team3309.robot.Robot;

import java.util.ArrayList;

public class DrivePath extends Command {

    private BiArcController biArcController;
    private double start;
    private boolean isInitialized = false;
    private final double TIMEOUT_SEC;

    public DrivePath(ArrayList<Waypoint> path, double timeoutSec) {
        requires(Robot.drive);
        for (Waypoint waypoint : path) {
            waypoint.setRadius(Robot.drive.inchesToEncoderCounts(waypoint.getRadius()));
        }
        biArcController = new BiArcController(path,
                Robot.drive.inchesToEncoderCounts(Constants.WHEELBASE_INCHES.toInches()));
        this.TIMEOUT_SEC = timeoutSec;
    }

    public DrivePath(ArrayList<Waypoint> path){
        this(path, Double.POSITIVE_INFINITY);
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
        DriveState driveState = new DriveState(Robot.drive.getEncoderPos(), Robot.drive.getAngPos());
        DriveSignal driveSignal = biArcController.update(driveState);
        Robot.drive.setLeftRight(driveSignal.getLeftMotor(), driveSignal.getRightMotor());
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
