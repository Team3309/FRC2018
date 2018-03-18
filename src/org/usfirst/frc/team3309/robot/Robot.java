package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3309.commands.subsystems.AssemblyLocation;
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

    private Compressor c;
    private OI oi;

    private Command autoCommand = null;
    public static Logger logger;

    private static boolean isLeftSwitch;
    private static boolean isRightSwitch;

    private static boolean isLeftScale;
    private static boolean isRightScale;


    private PowerDistributionPanel pdp;

    @Override
    public void robotInit() {
        setPeriod(0.005);
        pdp = new PowerDistributionPanel(0);
        AssemblyLocation.init();
        logger = Logger.getLogger("Robot");
        logger.info("robot init");
     //   BlackBox.initLog("beltbar current ", "amps");
        CameraServer.getInstance().startAutomaticCapture(0).setFPS(18);
        CameraServer.getInstance().startAutomaticCapture(1).setFPS(18);
        drive = new Drive();
        lift = new Lift();
        beltBar = new BeltBar();
        shooter = new Shooter();
        falconDoors = new FalconDoors();
        arms = new Arms();
        rollers = new Rollers();
        oi = new OI();
        c = new Compressor();
        LiveWindow.disableTelemetry(pdp);
        c.start();
        drive.reset();
        lift.setLiftShifter(true);
        falconDoors.setUp();
        sendToDashboard();
        drive.zeroNavx();
        Scheduler.getInstance().add(new LiftCheckLimits());
        AutoModeExecutor.displayAutos();
    }

    @Override
    public void autonomousInit() {
        Scheduler.getInstance().removeAll();
        if (DriverStation.getInstance().getGameSpecificMessage() != null) {
            isLeftSwitch = DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L';
            isRightSwitch = DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R';
            isLeftScale = DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L';
            isRightScale = DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'R';
        }
        falconDoors.setUp();
        c.stop();
        logger.info("autonomous init");
        drive.reset();
        lift.setHolderOut();
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

    private double start;

    @Override
    public void teleopInit() {
        start = Timer.getFPGATimestamp();
        c.start();
        logger.info("teleop init");
        if (autoCommand != null) {
            autoCommand.cancel();
        }
        drive.reset();
        drive.setHighGear();
        drive.changeToBrakeMode();
        falconDoors.setUp();
        lift.setHolderOut();
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
        if (Timer.getFPGATimestamp() - start >= 1) {
          //  BlackBox.writeLog(String.valueOf(Timer.getFPGATimestamp()), String.valueOf(beltBar.getCurrent()));
            start = Timer.getFPGATimestamp();
        }
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        falconDoors.setUp();
        drive.reset();
    }

    public void sendToDashboard() {
        drive.sendToDashboard();
        lift.sendToDashboard();
        beltBar.sendToDashboard();
        arms.sendToDashboard();
    }

    public static boolean isLeftSwitch() {
        return isLeftSwitch;
    }

    public static boolean isRightSwitch() {
        return isRightSwitch;
    }

    public static boolean isLeftScale() {
        return isLeftScale;
    }

    public static boolean isRightScale() {
        return isRightScale;
    }

}
