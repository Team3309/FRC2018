package org.usfirst.frc.team3309.commands.subsystems.drive

import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.usfirst.frc.team3309.robot.Robot
import org.usfirst.frc.team4322.math.Path
import org.usfirst.frc.team4322.motion.Lookahead
import org.usfirst.frc.team4322.motion.PathFollower
import org.usfirst.frc.team4322.motion.RobotPositionIntegrator

class FollowPathCommand(private val path : Path, private val reverse : Boolean = false) : Command() {
    val pathFollower = PathFollower(path,reverse, PathFollower.Parameters(Lookahead(3.0,24.0,6.0,75.0),
            0.0,3.8,0.01224,0.02,1.0,0.06,75.0,110.0,0.75,12.0,3.0))

    init {
        requires(Robot.drive)
    }

    override fun initialize() {
        RobotPositionIntegrator.reset()
    }

    override fun execute() {
        Robot.drive.changeToVelocityMode()
<<<<<<< HEAD
        val out = pathFollower.execute(Timer.getFPGATimestamp())
        val outLeft = Robot.drive.inchesToEncoderCounts(out.first)
        val outRight = Robot.drive.inchesToEncoderCounts(out.second)
        SmartDashboard.putNumber("Left Target: ",outLeft)
        SmartDashboard.putNumber("Right Target: ",outRight)
        Robot.drive.setLeftRight(outLeft,outRight)
        pathFollower.execute(Timer.getFPGATimestamp())
=======
//        val out = pathFollower.execute(Timer.getFPGATimestamp())
//        SmartDashboard.putNumber("Left Target: ",Robot.drive.inchesToEncoderCounts(out.first))
//        SmartDashboard.putNumber("Right Target: ",Robot.drive.inchesToEncoderCounts(out.second))
        Robot.drive.setLeftRight(200000.0,200000.0)
>>>>>>> Auto-Commit
    }

    override fun isFinished(): Boolean {
        return false
    }
}