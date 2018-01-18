package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.Command;
import lib.controllers.drive.DrivePositionController;
import lib.controllers.drive.DriveSignal;
import lib.controllers.pid.PIDConstants;
import org.usfirst.frc.team3309.commands.ControlledCommand;
import org.usfirst.frc.team3309.robot.Robot;
import org.usfirst.frc.team3309.robot.RobotMap;

public class DriveForwardAuto extends Command {

    private PIDConstants pidConstants;
    private DrivePositionController  drivePositionController;

    private double goalPos;
    private double goalAngle;

    public DriveForwardAuto(double goalPos, double goalAngle) {
        pidConstants = new PIDConstants(RobotMap.DRIVE_POSITION_CONTROLLER_P_SCALE,
                RobotMap.DRIVE_POSITION_CONTROLLER_I_SCALE,
                RobotMap.DRIVE_POSITION_CONTROLLER_D_SCALE);
        drivePositionController = new DrivePositionController(pidConstants, pidConstants, new PIDConstants());
        requires(Robot.drive);
    }

    public DriveForwardAuto(double goalPos) {
        this(goalPos, 0);
    }

    @Override
    protected void initialize() {
        Robot.drive.changeToPercentMode();
    }

    @Override
    protected void execute() {
        DriveSignal driveSignal = drivePositionController.update(Robot.drive.getEncoderPos(),
                Robot.drive.encoderCountsToInches(goalPos), Robot.drive.getAngPos(), 0);
        Robot.drive.setLeftRight(driveSignal.getLeftMotor(), driveSignal.getRightMotor());
    }

    @Override
    protected boolean isFinished() {
        return drivePositionController.isFinished();
    }

}
