package com.sinius15.lights;

import java.util.UUID;

import com.sinius15.launchpad.OwnedLaunchpad;

public abstract class Effect{
	
	protected int row, colomn;
	protected final String UID;
	private OwnedLaunchpad launchpad;
	
	public Effect(OwnedLaunchpad pad, int row, int colomn){
		this.row = row;
		this.colomn = colomn;
		this.UID = UUID.randomUUID().toString();
		this.launchpad = pad;
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
	public void buttonDown(){};
	
	/**
	 * called when the button on the launchpad is pressed.
	 * This function is called on a sperate thread. 
	 * So you can occupy this thread without any problems.
	 * @param launchpad the launchpad to show the action on.
	 */
	public void buttonUp(){};
	
	public void setLedOn(int column, int row, int color){
		this.launchpad.setLedOn(column, row, color, this.UID);
	}
	
	public void setLedOff(int column, int row){
		this.launchpad.setLedOff(column, row, this.UID);
	}
	
	public void colorRow(int color, int row) {
		if(row < 0 || row > 8)
			return;
		for(int i = 0; i <= 8; i++){
			launchpad.setLedOn(i, row, color, UID);
		}
	}
	
	public void colorColomn(int color, int colomn) {
		for(int i = 0; i <= 8; i++){
			launchpad.setLedOn(colomn, i, color, UID);
		}
	}
	
	public void setLedIfPossible(int row, int colomn, int color){
		if(row < 0 || row > 8 || colomn < 0 || colomn > 8)
			return;
		launchpad.setLedOn(colomn, row, color, UID);
	}
	
	/**
	 * get an arry with the Options that this effect has.
	 * @return
	 */
	public Option<?>[] getOptions(){
		return null;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
