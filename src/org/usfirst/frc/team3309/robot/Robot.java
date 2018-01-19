package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team3309.subsystems.Drive;
import org.usfirst.frc.team3309.subsystems.Intake;
import org.usfirst.frc.team3309.subsystems.Lift;

import java.util.logging.Logger;

/**
 * @author Chase Blagden
 */
public class Robot extends IterativeRobot {

    public static Drive drive = new Drive();
    public static Intake intake = new Intake();
    public static Lift lift = new Lift();

    private Compressor c = new Compressor();

    private Command autoCommand = null;
    private Logger logger = Logger.getLogger("Robot");

    @Override
    public void robotInit() {
        logger.info("robot init");
        c.start();
        Robot.drive.sendToDashboard();
        AutoModeExecutor.displayAutos();
        Robot.drive.resetDrive();
    }

    @Override
    public void autonomousInit() {
        logger.info("autonomous init");
        Robot.drive.resetDrive();
        autoCommand = AutoModeExecutor.getAutoSelected();
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
        Robot.drive.resetDrive();
        Robot.drive.enableBrakeMode(false);
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        Robot.drive.sendToDashboard();
    }

    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }

}
