package org.usfirst.frc.team3309.commands.subsystems;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team3309.robot.Constants;
import org.usfirst.frc.team3309.robot.Robot;

public class ChangeAssemblyGoalCubeUp extends InstantCommand {

    private final double ONE_CUBE_POS_UP = 3000;

    public void execute() {
        double newLiftGoal = Robot.lift.getGoalPos() + ONE_CUBE_POS_UP;
        if (newLiftGoal > Constants.MAX_LIFT_POS) {
            newLiftGoal = Constants.MAX_LIFT_POS - 2000;
        } else if (newLiftGoal < 0) {
            newLiftGoal = 0;
        }
        Robot.lift.setLiftPos(newLiftGoal);
    }

}
