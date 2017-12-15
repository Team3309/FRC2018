package library.controllers.drive;

import java.util.LinkedList;
import java.util.List;

import library.controllers.Controller;
import library.controllers.statesandsignals.InputState;
import library.controllers.statesandsignals.OutputSignal;

public class DriveVelocityControllerWithWaypoints extends Controller {
	
	private List<Waypoint> waypoints = new LinkedList<Waypoint>();

	@Override
	public boolean isCompletable() {
		return false;
	}

	@Override
	public OutputSignal getOutputSignal(InputState input) {
		
		for (Waypoint waypoint : waypoints) {
			
		}
		
		
		return null;
	}

	@Override
	public void reset() {
		
	}
	
	
}
