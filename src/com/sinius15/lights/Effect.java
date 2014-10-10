package com.sinius15.lights;

import java.util.UUID;

import com.sinius15.launchpad.OwnedLaunchpad;

public abstract class Effect {
	
	protected int row, column;
	protected final String UID;
	private OwnedLaunchpad launchpad;
	
	public Effect(OwnedLaunchpad pad, int row, int colomn) {
		this.row = row;
		this.column = colomn;
		this.UID = UUID.randomUUID().toString();
		this.launchpad = pad;
	}
	
	/**
	 * The name of the Action.
	 * 
	 * @return the name of this action.
	 */
	public abstract String getName();
	
	/**
	 * A description of this Action. Line seperator is
	 * {@link System #lineSeparator()}
	 * 
	 * @return the Description.
	 */
	public abstract String getDescription();
	
	/**
	 * called when the button on the launchpad is pressed. This function is
	 * called on a sperate thread. So you can occupy this thread without any
	 * problems.
	 * 
	 * @param launchpad
	 *            the launchpad to show the action on.
	 */
	public void buttonDown() {};
	
	/**
	 * called when the button on the launchpad is pressed. This function is
	 * called on a sperate thread. So you can occupy this thread without any
	 * problems.
	 * 
	 * @param launchpad
	 *            the launchpad to show the action on.
	 */
	public void buttonUp() {};
	
	/**
	 * Turns on a led on the launchpad. When {@link #COLOR_TRANSPARANT} is
	 * selected, nothing is set!
	 * 
	 * @param column
	 *            the colomn on the launchpad where the left colomn is 0 and the
	 *            right colomn with the round buttons is 8
	 * @param row
	 *            the row on the launchpad where the top rowh with the round
	 *            buttons is 0 and the bottom row is 8
	 * @param color
	 *            the colour of the led. Values are found in the static feelds
	 *            in the Launchpad class
	 * @author Sinius15
	 */
	public void setLedOn(int column, int row, int color) {
		this.launchpad.setLedOn(column, row, color, this.UID);
	}
	
	/**
	 * Turns off a led on the launchpad. <br>
	 * 
	 * @param column
	 *            the colomn on the launchpad where the left colomn is 0 and the
	 *            right colomn with the round buttons is 8
	 * @param row
	 *            the row on the launchpad where the top rowh with the round
	 *            buttons is 0 and the bottom row is 8
	 * @author Sinius15
	 */
	public void setLedOff(int column, int row) {
		this.launchpad.setLedOff(column, row, this.UID);
	}
	
	/**
	 * Colors a whole row. If the row does not exist, it will return. 
	 * @param color the color of the led.
	 * @param row the row to set on.
	 */
	public void colorRow(int color, int row) {
		if (row < 0 || row > 8)
			return;
		for (int i = 0; i <= 8; i++) {
			setLedOn(i, row, color);
		}
	}
	
	/**
	 * Colors a whole row. If the column does not exist, it will return. 
	 * @param color the color of the led.
	 * @param column the row to set on.
	 */
	public void colorColumn(int color, int column) {
		if (column < 0 || column > 8)
			return;
		for (int i = 0; i <= 8; i++) {
			setLedOn(column, i, color);
		}
	}
	
	/**
	 * Does exacly the same as {@link Effect#setLedOn(int, int, int)} but if the
	 * coordiante not exist, it returns.
	 * 
	 * @param row
	 *            the row on the launchpad.
	 * @param column
	 *            the colomn on the launchpad.
	 * @param color
	 *            the color to set the button.
	 */
	public void setLedIfPossible(int row, int column, int color) {
		if (row < 0 || row > 8 || column < 0 || column > 8)
			return;
		setLedOn(column, row, color);
	}
	
	/**
	 * get an arry with the Options that this effect has.
	 * 
	 * @return
	 */
	public Option<?>[] getOptions() {
		return null;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
