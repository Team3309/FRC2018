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
    val pathFollower = PathFollower(path,reverse, PathFollower.Parameters(Lookahead(6.0,18.0,6.0,60.0),
            0.000013309,1.8,0.015,0.02,1.0,0.05,60.0,44.14,0.75,12.0,3.0))

    init {
        requires(Robot.drive)
    }

    override fun initialize() {
        RobotPositionIntegrator.reset()
    }

    override fun execute() {
        Robot.drive.changeToVelocityMode()
        val out = pathFollower.execute(Timer.getFPGATimestamp())
        SmartDashboard.putNumber("Left Target: ",Robot.drive.inchesToEncoderCounts(out.first))
        SmartDashboard.putNumber("Right Target: ",Robot.drive.inchesToEncoderCounts(out.second))
        Robot.drive.setLeftRight(Robot.drive.inchesToEncoderCounts(out.first),Robot.drive.inchesToEncoderCounts(out.second))
    }

    override fun isFinished(): Boolean {
        return pathFollower.isFinished
    }
}