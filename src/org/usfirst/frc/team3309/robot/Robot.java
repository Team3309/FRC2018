package org.usfirst.frc.team3309.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.hal.PDPJNI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftCheckLimits;
import org.usfirst.frc.team3309.lib.communications.BlackBox;
import org.usfirst.frc.team3309.subsystems.*;

import java.sql.Time;
import java.util.logging.Logger;

/**
 * @author Chase Blagden
 */
public class Robot extends TimedRobot {

    public static Drive drive;
    public static Lift lift;
    public static BeltBar beltBar;
    public static Shooter shooter;
    public static FalconDoors falconDoors;
    public static Arms arms;
    public static Rollers rollers;

    private UsbCamera cam;
    private Compressor c;
    private OI oi;

    private Command autoCommand = null;
    public static Logger logger;

    @Override
    public void robotInit() {
    //    BlackBox.initLog("Current log", "Time", "15", "13", "14", "0", "1", "3");
        setPeriod(0.01);
        logger = Logger.getLogger("Robot");
        logger.info("robot init");
        cam = CameraServer.getInstance().startAutomaticCapture();
        cam.setFPS(15);
        drive = new Drive();
        lift = new Lift();
        beltBar = new BeltBar();
        shooter = new Shooter();
        falconDoors = new FalconDoors();
        arms = new Arms();
        rollers = new Rollers();
        oi = new OI();
        c = new Compressor();
        c.start();
        sendToDashboard();
        drive.reset();
        AutoModeExecutor.displayAutos();
        lift.setLiftShifter(false);
    }

    @Override
    public void autonomousInit() {
        c.stop();
        logger.info("autonomous init");
        drive.reset();
        lift.setLiftShifter(false);
        lift.reset();
        autoCommand = AutoModeExecutor.getAutoSelected();
        logger.info("Running " + autoCommand.getName());
        if (autoCommand != null) {
            autoCommand.start();
        }
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        sendToDashboard();
    }

    double start;

    @Override
    public void teleopInit() {
        lift.setLiftShifter(false);
        c.start();
        logger.info("teleop init");
        if (autoCommand != null) {
            autoCommand.cancel();
        }
        drive.reset();
        drive.setHighGear();
        drive.changeToBrakeMode();
        lift.setLiftShifter(false);
        Scheduler.getInstance().removeAll();
        Scheduler.getInstance().add(new LiftCheckLimits());
        start = Timer.getFPGATimestamp();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        sendToDashboard();
        SmartDashboard.putData(beltBar);
        SmartDashboard.putData(shooter);
        SmartDashboard.putData(falconDoors);
        SmartDashboard.putData(arms);
        SmartDashboard.putData(rollers);

        if (beltBar.isCubePresent() &&
                arms.isArmsClosed()) {
            OI.operatorRemote.setRumble(1);
            OI.driverRemote.setRumble(1);
        } else {
            OI.operatorRemote.setRumble(0);
            OI.driverRemote.setRumble(0);
        }

        double now = Timer.getFPGATimestamp();

 /*       if (now - start > 0.5) {
            // 15 13 14 0 1 3
            BlackBox.writeLog(
                    String.valueOf(Timer.getFPGATimestamp()),
                    String.valueOf(PDPJNI.getPDPTotalCurrent(15)),
                    String.valueOf(PDPJNI.getPDPTotalCurrent(13)),
                    String.valueOf(PDPJNI.getPDPTotalCurrent(14)),
                    String.valueOf(PDPJNI.getPDPTotalCurrent(0)),
                    String.valueOf(PDPJNI.getPDPTotalCurrent(1)),
                    String.valueOf(PDPJNI.getPDPTotalCurrent(3)));
            start = Timer.getFPGATimestamp();
        }*/

    }

    @Override
    public void disabledPeriodic() {
        drive.reset();
    }

    public void sendToDashboard() {
        drive.sendToDashboard();
        lift.sendToDashboard();
        beltBar.sendToDashboard();
        arms.sendToDashboard();
    }

    public static boolean isLeftSwitch() {
        return DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L';
    }

    public static boolean isRightSwitch() {
        return DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R';
    }

    public static boolean isLeftScale() {
        return DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L';
    }

    public static boolean isRightScale() {
        return DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'R';
    }

}
