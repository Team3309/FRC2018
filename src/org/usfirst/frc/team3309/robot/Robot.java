package org.usfirst.frc.team3309.robot;

import edu.wpi.first.wpilibj.Compressor;
import lib.communications.BlackBox;

import org.usfirst.frc.team3309.subsystems.Drive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * @author Chase.Blagden
 */
public class Robot extends IterativeRobot {


    public static Drive drive = new Drive();
    private Compressor c = new Compressor();
    private Command autoCommand = null;
    public OI oi = new OI();

    @Override
    public void robotInit() {
        //   c.start();
        Robot.drive.sendToDashboard();
        AutoModeExecutor.displayAutos();
        BlackBox.initLog("PursuitTest", "X pos", "Y pos");
        System.out.println("Starting robot");
        Robot.drive.resetDrive();
    }

    @Override
    public void autonomousInit() {
        Robot.drive.resetDrive();
        System.out.println("Starting auto");
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
        System.out.println("Starting teleop");
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
