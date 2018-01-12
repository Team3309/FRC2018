package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team3309.subsystems.Drive;

import java.util.logging.Logger;

/**
 * @author Chase Blagden
 */
public class Robot extends IterativeRobot {

<<<<<<< HEAD
	private AutoRoutine auto;

	@Override
	public void robotInit() {
		Systems.add(Drive.getInstance());
		Systems.init();
		BlackBox.initLog(
				"3309"
						+ String.valueOf(DriverStation.getInstance()
								.getAlliance()), "X position", "Y position");
		AutoModeExecutor.displayAutos();
		Sensors.resetDrive();
	}

	@Override
	public void autonomousInit() {
		Systems.initAuto();
		auto = AutoModeExecutor.getAutoSelected();
		auto.initialize();
	}

	@Override
	public void autonomousPeriodic() {
		auto.update();
		Sensors.updateDrive();
		Systems.updateAuto();
		Systems.sendToDashboard();
		BlackBox.writeLog(String.valueOf(Drive.getInstance().getInches(
				Sensors.getXPos())), String.valueOf(Drive.getInstance()
				.getInches(Sensors.getYPos())));
	}

	@Override
	public void teleopInit() {
		Systems.initTeleop();
	}

	@Override
	public void teleopPeriodic() {
		Systems.sendToDashboard();
	}

	@Override
	public void disabledPeriodic() {
		Systems.sendToDashboard();
	}
=======
    public static Drive drive = new Drive();

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

>>>>>>> command-based
}
