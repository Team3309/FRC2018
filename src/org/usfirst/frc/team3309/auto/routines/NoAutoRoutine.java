package org.usfirst.frc.team3309.auto.routines;

import org.usfirst.frc.team3309.auto.AutoRoutine;

public class NoAutoRoutine extends AutoRoutine {

	@Override
	public void redRoutine() {
		System.out.println("No auto");
	}

	@Override
	public void blueRoutine() {
		redRoutine();
	}

}
