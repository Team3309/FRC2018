package org.usfirst.frc.team3309.commands.autos

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.InstantCommand
import edu.wpi.first.wpilibj.command.WaitForChildren
import org.usfirst.frc.team3309.commands.subsystems.drive.FollowPathCommand
import org.usfirst.frc.team4322.math.PathBuilder
import java.util.ArrayList


class SimplePathDriveTest : CommandGroup() {
init {
    val waypoints = ArrayList<PathBuilder.Waypoint>()
    waypoints.add(PathBuilder.Waypoint(0.0, 0.0, 0.0, 0.0))
//        waypoints.add(new PathBuilder.Waypoint(72.0,0.0,0.0,200.0));
//        waypoints.add(new PathBuilder.Waypoint(196,0.0,0.0,100.0));
    waypoints.add(PathBuilder.Waypoint(18.0, -5.0, 0.0, 30.0))
    waypoints.add(PathBuilder.Waypoint(29.0, -7.0, 0.0, 40.0))
    waypoints.add(PathBuilder.Waypoint(36.0, -10.0, 0.0, 50.0))
    waypoints.add(PathBuilder.Waypoint(100.0, -25.0, 0.0, 60.0))
    waypoints.add(PathBuilder.Waypoint(130.0, -35.0, 0.0, 75.0,"midpoint"))
    waypoints.add(PathBuilder.Waypoint(240.0, -55.0, 0.0, 75.0))

    val path = PathBuilder.buildPathFromWaypoints(waypoints)
    addParallel(object : CommandGroup(){
            init {
                addSequential(object : Command() {
                    override fun isFinished(): Boolean {
                        return path.hasPassedMarker("midpoint")
                    }
                })
            }
        })
    addSequential(FollowPathCommand(path, false))
    addSequential(WaitForChildren())

}
}