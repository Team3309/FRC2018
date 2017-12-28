package org.usfirst.frc.team3309.auto.routines;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team3309.auto.AutoRoutine;
import org.usfirst.frc.team3309.auto.DriveAction;
import org.usfirst.frc.team3309.auto.actions.TestAction;

import library.controllers.drive.DrivePositionController;
import library.controllers.drive.DriveVelocityControllerWithSetpoints;
import library.controllers.drive.VelocityChangePoint;

public class TestRoutine extends AutoRoutine {

	@Override
	public void redRoutine() {
		DriveVelocityControllerWithSetpoints c = new DriveVelocityControllerWithSetpoints(
				50000);
		List<VelocityChangePoint> setpoints = new ArrayList<VelocityChangePoint>();
		setpoints.add(new VelocityChangePoint(0, 1000));
		setpoints.add(new VelocityChangePoint(30000, 600, 400));
		setpoints.add(new VelocityChangePoint(45000, 500));
		c.setWaypoints(setpoints);
		addDriveAction(new DriveAction(c, 6.0));
		addAction(new TestAction(0));
	}

	@Override
	public void blueRoutine() {
		DrivePositionController c = new DrivePositionController(5000);
		addDriveAction(new DriveAction(c, 5.0));
		addAction(new TestAction(0));
	}

}
