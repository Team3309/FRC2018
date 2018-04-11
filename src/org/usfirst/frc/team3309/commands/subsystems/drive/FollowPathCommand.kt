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
    val pathFollower = PathFollower(path,reverse, PathFollower.Parameters(Lookahead(12.0,36.0,6.0,70.0),
            0.000013309,10.0,0.028,0.02,1.0,0.06,70.0,70.0,0.75,16.0,9.0))

    init {
        requires(Robot.drive)
    }

    override fun initialize() {
        RobotPositionIntegrator.reset()
    }

    override fun execute() {
        Robot.drive.changeToVelocityMode()
        val out = pathFollower.execute(Timer.getFPGATimestamp())
        val outLeft = Robot.drive.inchesToEncoderCounts(out.first)
        val outRight = Robot.drive.inchesToEncoderCounts(out.second)
        SmartDashboard.putNumber("Left Target: ",outLeft)
        SmartDashboard.putNumber("Right Target: ",outRight)
        Robot.drive.setLeftRight(outLeft,outRight)
    }

    override fun isFinished(): Boolean {
        return pathFollower.isFinished
    }
}