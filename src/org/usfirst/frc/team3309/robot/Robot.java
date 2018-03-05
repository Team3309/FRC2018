package org.usfirst.frc.team3309.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftCheckLimits;
import org.usfirst.frc.team3309.subsystems.*;

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
        drive.changeToCoastMode();
        lift.setLiftShifter(false);
        Scheduler.getInstance().removeAll();
        Scheduler.getInstance().add(new LiftCheckLimits());
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
