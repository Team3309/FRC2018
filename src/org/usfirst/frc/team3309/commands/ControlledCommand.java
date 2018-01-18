package org.usfirst.frc.team3309.commands;

import edu.wpi.first.wpilibj.command.Command;
import lib.controllers.Controller;
import lib.controllers.Finishable;

public abstract class ControlledCommand<T extends Controller & Finishable> extends Command implements Finishable {

    private T controller;

    public ControlledCommand(T controller) {
        this.controller = controller;
    }

    @Override
    public boolean isFinished() {
        return controller.isFinished();
    }
}
