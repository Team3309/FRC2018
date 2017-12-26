package org.usfirst.frc.team3309.auto;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import library.controllers.drive.DriveCurvatureFollowerController;
import library.controllers.drive.DrivePositionController;
import library.controllers.drive.DriveVelocityControllerWithSetpoints;
import library.controllers.drive.VelocityChangePoint;
import library.controllers.drive.Waypoint;

import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.tree.ImmutableNode;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Chase.Blagden Auto routine that reads selected auto and loads actions
 *         and driveAction.
 */
public class FileReaderAutoRoutine extends AutoRoutine {

	private String allianceColor;
	private static SendableChooser<File> autoOptions = new SendableChooser<>();

	@Override
	public void redRoutine() {
		allianceColor = "red";
		loadAuto();
	}

	@Override
	public void blueRoutine() {
		allianceColor = "blue";
		loadAuto();
	}

	/*
	 * Loops through auto files and sends them to dashboard
	 */
	public static void displayAutos() {
		File[] autoFiles = new File("autos").listFiles();
		for (File auto : autoFiles) {
			autoOptions.addObject(auto.getName(), auto);
		}
		autoOptions.addDefault("Default", new File("autos/NoAuto.xml"));
		SmartDashboard.putData("Auto", autoOptions);
	}

	/*
	 * Using Apache library, parses auto file for key values. Accounts for
	 * missing values. https://www.apache.org/
	 */
	@SuppressWarnings("unchecked")
	public void loadAuto() {
		Parameters params = new Parameters();
		File propertiesFile = new File(autoOptions.getSelected().getName());
		FileBasedConfigurationBuilder<XMLConfiguration> builder = new FileBasedConfigurationBuilder<>(
				XMLConfiguration.class).configure(params.xml().setFile(
				propertiesFile));
		try {
			HierarchicalConfiguration<ImmutableNode> config = builder
					.getConfiguration();
			if (!config.configurationAt(allianceColor).isEmpty()) {
				for (HierarchicalConfiguration<ImmutableNode> auto : config
						.childConfigurationsAt(allianceColor)) {

					if (!auto.configurationAt("actions").isEmpty()) {
						for (HierarchicalConfiguration<ImmutableNode> action : auto
								.childConfigurationsAt("actions")) {
							Class<? extends Action> newAction;
							try {
								newAction = (Class<? extends Action>) Class
										.forName(action.getString("action"));
								addAction(newAction.cast(Action.class));
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
								System.err.println("Action does not exist.");
							}
						}
					}

					if (!auto.configurationAt("driveAction").isEmpty()) {
						for (HierarchicalConfiguration<ImmutableNode> driveActions : auto
								.childConfigurationsAt("driveAction")) {
							double timeout = driveActions
									.containsKey("timeout") ? driveActions
									.getDouble("timeout") : 0;
							if (driveActions.configurationAt("waypoints")
									.isEmpty()) {
								double goal = driveActions.containsKey("goal") ? driveActions
										.getDouble("goal") : 0;
								if (!driveActions.configurationAt("setpoints")
										.isEmpty()) {
									List<VelocityChangePoint> setpoints = new LinkedList<>();
									for (HierarchicalConfiguration<ImmutableNode> waypoint : driveActions
											.childConfigurationsAt("setpoints")) {
										double enc = waypoint
												.containsKey("enc") ? waypoint
												.getDouble("enc") : 0;
										double leftVel = waypoint
												.containsKey("leftvel") ? waypoint
												.getDouble("leftvel")
												: waypoint.containsKey("vel") ? waypoint
														.getDouble("vel") : 0;
										double rightVel = waypoint
												.containsKey("rightvel") ? waypoint
												.getDouble("rightvel")
												: waypoint.containsKey("vel") ? waypoint
														.getDouble("vel") : 0;
										double angle = waypoint
												.containsKey("goalAngle") ? waypoint
												.getDouble("goalAngle") : null;
										setpoints.add(new VelocityChangePoint(
												enc, leftVel, rightVel, angle));
									}
									DriveVelocityControllerWithSetpoints x = new DriveVelocityControllerWithSetpoints(
											goal);
									x.setWaypoints(setpoints);
									addDriveAction(new DriveAction(x, timeout));
								} else {
									DrivePositionController x = new DrivePositionController(
											goal);
									addDriveAction(new DriveAction(x, timeout));
								}
							} else {
								List<Waypoint> waypoints = new LinkedList<>();
								double finalHeading = driveActions
										.containsKey("heading") ? driveActions
										.getDouble("heading") : 0.0;
								for (HierarchicalConfiguration<ImmutableNode> waypoint : driveActions
										.childConfigurationsAt("waypoints")) {
									double x = waypoint.containsKey("x") ? waypoint
											.getDouble("x") : 0.0;
									double y = waypoint.containsKey("y") ? waypoint
											.getDouble("y") : 0.0;
									waypoints.add(new Waypoint(x, y));
								}
								DriveCurvatureFollowerController x = new DriveCurvatureFollowerController(
										(Waypoint[]) waypoints.toArray(),
										finalHeading);
								addDriveAction(new DriveAction(x, timeout));
							}
						}
					}
				}
			}
		} catch (ConfigurationException e) {
			e.printStackTrace();
			super.stopAutoThread();
		}
	}

}