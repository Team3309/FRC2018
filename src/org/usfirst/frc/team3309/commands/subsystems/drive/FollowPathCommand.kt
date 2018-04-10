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
    val pathFollower = PathFollower(path,reverse, PathFollower.Parameters(Lookahead(12.0,24.0,70.0,120.0),
            0.0,5.0,0.03,0.02,1.0,0.05,120.0,120.0,0.75,12.0,9.0))

    override fun initialize() {
        RobotPositionIntegrator.reset()
    }

    override fun execute() {
        Robot.drive.changeToVelocityMode()
        val out = pathFollower.execute(Timer.getFPGATimestamp())
        SmartDashboard.putNumber("Left Target: ",Robot.drive.inchesToEncoderCounts(out.first))
        SmartDashboard.putNumber("Right Target: ",Robot.drive.inchesToEncoderCounts(out.second))
//        Robot.drive.setLeftRight(Robot.drive.inchesToEncoderCounts(out.first),Robot.drive.inchesToEncoderCounts(out.second))
        Robot.drive.setLeftRight(10000.0,10000.0)
    }

    override fun isFinished(): Boolean {
        return pathFollower.isFinished
    }
}