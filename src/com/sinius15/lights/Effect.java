package com.sinius15.lights;

import com.sinius15.launchpad.BufferedLaunchpad;

public abstract class Effect{
	
	protected int row, colomn;
	
	public Effect(int row, int colomn){
		this.row = row;
		this.colomn = colomn;
	}
	
	/**
	 * The name of the Action.
	 * @return the name of this action.
	 */
	public abstract String getName();
	
	/**
	 * A description of this Action. Line seperator is 
	 * {@link System #lineSeparator()}
	 * @return the Description.
	 */
	public abstract String getDescription();
	
	/**
	 * called when the button on the launchpad is pressed.
	 * This function is called on a sperate thread. 
	 * So you can occupy this thread without any problems.
	 * @param launchpad the launchpad to show the action on.
	 */
	public void buttonDown(BufferedLaunchpad launchpad){};
	
	/**
	 * called when the button on the launchpad is pressed.
	 * This function is called on a sperate thread. 
	 * So you can occupy this thread without any problems.
	 * @param launchpad the launchpad to show the action on.
	 */
	public void buttonUp(BufferedLaunchpad launchpad){};
	
	public Option<?>[] getOptions(){
		return null;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
