package org.usfirst.frc.team3309.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveArc;
import org.usfirst.frc.team3309.commands.subsystems.drive.DriveStraightProperly;
import org.usfirst.frc.team3309.lib.math.Length;
import org.usfirst.frc.team3309.robot.Robot;

public class ScaleOnlyAuto extends CommandGroup {

    private boolean isStartLeft = false;

    public ScaleOnlyAuto(boolean isStartLeft) {
        this.isStartLeft = isStartLeft;
    }

    @Override
    public synchronized void start() {
        if (Robot.isLeftScale() && isStartLeft) {
            addSequential(new DriveStraightProperly(204, 30000));
            addSequential(new DriveArc(Length.fromInches(30), 30, 26000, false));
            addSequential(new DriveStraightProperly(5, 10000));
        } else if (Robot.isRightSwitch() && !isStartLeft) {
            addSequential(new DriveStraightProperly(115, 30000));
            addSequential(new DriveArc(Length.fromInches(30), -90, 26000, false));
            addSequential(new DriveStraightProperly(144, 30000));
            addSequential(new DriveArc(Length.fromInches(30), 90, 26000, false));
            addSequential(new DriveStraightProperly(33, 30000));

        }
        super.start();
    }
}
