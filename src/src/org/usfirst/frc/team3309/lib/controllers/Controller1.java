package src.org.usfirst.frc.team3309.lib.controllers;

import org.usfirst.frc.team3309.lib.controllers.Controller;

public abstract class Controller1<R, T> extends Controller {

    public abstract R update(T t);

}
