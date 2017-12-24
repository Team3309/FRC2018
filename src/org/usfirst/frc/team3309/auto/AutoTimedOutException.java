package org.usfirst.frc.team3309.auto;

public class AutoTimedOutException extends Exception {

	private static final long serialVersionUID = 913914704738544570L;

	@Override
	public void printStackTrace() {
		super.printStackTrace();
		System.err.println("Auto timer extended past timeout.");
	}
	
}
