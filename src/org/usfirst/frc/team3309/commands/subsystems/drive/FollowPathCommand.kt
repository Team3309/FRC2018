package org.usfirst.frc.team3309.commands.subsystems.drive

import edu.wpi.first.wpilibj.command.Command
import org.usfirst.frc.team4322.math.Path
import org.usfirst.frc.team4322.motion.Lookahead
import org.usfirst.frc.team4322.motion.PathFollower

class FollowPathCommand(private val path : Path, private val reverse : Boolean = false) : Command() {
    val pathFollower = PathFollower(path,reverse, PathFollower.Parameters(Lookahead(12.0,24.0,9.0,120.0),
            0.0,5.0,0.03,0.02,1.0,0.05,120.0,120.0,0.75,12.0,9.0))

    override fun initialize() {
        pathFollower
    }

    override fun execute() {

    }

    override fun isFinished(): Boolean {
        return pathFollower.isFinished
    }
}