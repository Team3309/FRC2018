package org.usfirst.frc.team3309.lib.controllers;

public abstract class Controller2<R, T1, T2> extends Controller {

    public abstract R update(T1 t1, T2 t2);
}
