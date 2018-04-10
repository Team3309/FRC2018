package org.usfirst.frc.team3309.commands.autos

import edu.wpi.first.wpilibj.command.InstantCommand
import org.usfirst.frc.team3309.commands.subsystems.drive.FollowPathCommand
import org.usfirst.frc.team4322.math.PathBuilder


class SimplePathDriveTest : InstantCommand() {
    override fun start() {
        val waypoints = mutableListOf<PathBuilder.Waypoint>()
        waypoints.add(PathBuilder.Waypoint(0.0,0.0,0.0,0.0))
        waypoints.add(PathBuilder.Waypoint(0.0,12.0*6,0.0,0.0))
        val path = PathBuilder.buildPathFromWaypoints(waypoints)
        FollowPathCommand(path,false).start()

    }
}