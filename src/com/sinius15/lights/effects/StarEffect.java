package com.sinius15.lights.effects;

import com.sinius15.launchpad.BufferedLaunchpad;
import com.sinius15.launchpad.Launchpad;
import com.sinius15.lights.Effect;

public class StarEffect extends Effect {
	
	private int color;
	
	public StarEffect(int row, int colomn, String saveData){
		super(row, colomn, saveData);
		if(saveData == null)
			this.color = Launchpad.COLOR_GREEN_FULL;
		else
			this.color = Integer.parseInt(saveData);
	}
	
	@Override
	public String getName() {
		return "Star";
	}
	
	@Override
	public String getDescription() {
		return "Shows a star around the place where you press the button.";
	}
	
	@Override
	public void buttonDown(BufferedLaunchpad launchpad) {
		setLightIfExist(launchpad, row, colomn, true);
		setLightIfExist(launchpad, row+1, colomn, true);
		setLightIfExist(launchpad, row-1, colomn, true);
		setLightIfExist(launchpad, row, colomn+1, true);
		setLightIfExist(launchpad, row, colomn-1, true);
	}
	
	@Override
	public void buttonUp(BufferedLaunchpad launchpad) {
		setLightIfExist(launchpad, row, colomn, false);
		setLightIfExist(launchpad, row+1, colomn, false);
		setLightIfExist(launchpad, row-1, colomn, false);
		setLightIfExist(launchpad, row, colomn+1, false);
		setLightIfExist(launchpad, row, colomn-1, false);
	}
	
	private void setLightIfExist(Launchpad pad, int row, int col, boolean on){
		if(row < 0 || row > 8 || col < 0 || col > 8)
			return;
		if(on)
			pad.setLedOn(col, row, color);
		else
			pad.setLedOff(col, row);
	}
	
}
