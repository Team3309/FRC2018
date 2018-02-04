package org.usfirst.frc.team3309.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team3309.commands.subsystems.lift.LiftCheckLimits;
import org.usfirst.frc.team3309.subsystems.*;

import java.util.logging.Logger;

/**
 * @author Chase Blagden
 */
public class Robot extends IterativeRobot {

    public static Drive drive;
    public static Intake intake;
    public static Lift lift;
    public static BeltBar beltBar;
    public static Shooter shooter;
    public static FalconDoors falconDoors;

    private UsbCamera cam;
    private Compressor c;
    private OI oi;

    private Command autoCommand = null;
    public static Logger logger = Logger.getLogger("Robot");

    @Override
    public void robotInit() {
        logger.info("robot init");
        cam = CameraServer.getInstance().startAutomaticCapture();
        cam.setFPS(15);
        drive = new Drive();
        intake = new Intake();
        lift = new Lift();
        beltBar = new BeltBar();
        shooter = new Shooter();
        falconDoors = new FalconDoors();
        oi = new OI();
        c = new Compressor();
        c.start();
        drive.sendToDashboard();
        reset();
        AutoModeExecutor.displayAutos();
    }

    @Override
    public void autonomousInit() {
        logger.info("autonomous init");
        reset();
        autoCommand = AutoModeExecutor.getAutoSelected();
        logger.info("Running " + autoCommand.getName());
        if (autoCommand != null) {
            autoCommand.start();
        }
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        Robot.drive.sendToDashboard();
    }

    @Override
    public void teleopInit() {
        logger.info("teleop init");
        if (autoCommand != null) {
            autoCommand.cancel();
        }
        reset();
        drive.setHighGear();
        drive.changeToBrakeMode();
        new LiftCheckLimits().start();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        drive.sendToDashboard();
    }

    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }

    @Override
    public void disabledPeriodic() {
        drive.resetDrive();
    }

    public void reset() {
        drive.resetDrive();
    }

}
