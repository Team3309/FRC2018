package org.usfirst.frc.team3309.auto.routines;

import java.util.LinkedList;
import java.util.List;

import library.controllers.drive.DrivePositionController;
import library.controllers.drive.DriveVelocityControllerWithWaypoints;
import library.controllers.drive.Waypoint;

import org.usfirst.frc.team3309.auto.AutoRoutine;
import org.usfirst.frc.team3309.auto.DriveAction;
import org.usfirst.frc.team3309.auto.actions.TestAction;

public class TestRoutine extends AutoRoutine {

	@Override
	public void redRoutine() {
		DriveVelocityControllerWithWaypoints c = new DriveVelocityControllerWithWaypoints(50000);
		List<Waypoint> waypoints = new LinkedList<Waypoint>();
		waypoints.add(new Waypoint(1000, 0));
		waypoints.add(new Waypoint(600, 400, 30000));
		waypoints.add(new Waypoint(500, 500, 45000));
		c.setWaypoints(waypoints);
		addDriveAction(new DriveAction(c, 6.0));
		addAction(new TestAction(0));
	}

	@Override
	public void blueRoutine() {
		DrivePositionController c = new DrivePositionController(50000);
		addDriveAction(new DriveAction(c, 5.0));
		addAction(new TestAction(0));
	}

}
