package org.usfirst.frc.team3309.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
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
        logger = Logger.getLogger("Robot");
        logger.info("robot init");
        cam = CameraServer.getInstance().startAutomaticCapture();
        cam.setFPS(30);
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
        drive.sendToDashboard();
        drive.reset();
        AutoModeExecutor.displayAutos();
    }

    @Override
    public void autonomousInit() {
        logger.info("autonomous init");
        beltBar.reset();
        drive.reset();
        lift.setLiftShifter(false);
        lift.reset();
        autoCommand = AutoModeExecutor.getAutoSelected();
        logger.info("Running " + autoCommand.getName());
        if (autoCommand != null) {
            System.out.println("Starting");
            autoCommand.start();
        }
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        drive.sendToDashboard();
        lift.sendToDashboard();
    }

    @Override
    public void teleopInit() {
        logger.info("teleop init");
        if (autoCommand != null) {
            autoCommand.cancel();
        }
        drive.reset();
        drive.setHighGear();
        drive.changeToCoastMode();
        lift.setLiftShifter(false);
        Scheduler.getInstance().add(new LiftCheckLimits());
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        drive.sendToDashboard();
        lift.sendToDashboard();
        beltBar.sendToDashboard();
        SmartDashboard.putData(beltBar);
        SmartDashboard.putData(shooter);
        SmartDashboard.putData(falconDoors);
        SmartDashboard.putData(arms);
        SmartDashboard.putData(rollers);
    }

    @Override
    public void disabledPeriodic() {
        drive.reset();
        lift.reset();
        lift.setLiftShifter(false);
    }

    public static boolean isLeftSwitch() {
        return DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L';
    }

    public static boolean isLeftScale() {
        return DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L';
    }

}
