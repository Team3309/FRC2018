package lib.controllers;

/*
 * @author Chase Blagden
 * <p>generic setup for system controller
 * */
public abstract class Controller {

	private String name = "Default Controller Name";

	public abstract void update();

	public void sendToDashboard() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


}
