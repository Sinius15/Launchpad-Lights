package com.sinius15.lights.effects;

import com.sinius15.launchpad.BufferedLaunchpad;
import com.sinius15.launchpad.Launchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Option;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.ColorOption;
import com.sinius15.lights.options.SpeedOption;

public class ExplodingCross extends Effect{
	
	@Save
	public ColorOption colorSelector;
	@Save
	public SpeedOption speedSelector;
	
	public ExplodingCross(int row, int colomn) {
		super(row, colomn);
		colorSelector = new ColorOption();
		speedSelector = new SpeedOption(300);
	}
	
	@Override
	public void buttonDown(BufferedLaunchpad launchpad) {
		for(int i = 0; i < 5; i++){
			setLightIfExist(launchpad, 4-i, 3, true);
			setLightIfExist(launchpad, 4-i, 4, true);
			
			setLightIfExist(launchpad, 5+i, 3, true);
			setLightIfExist(launchpad, 5+i, 4, true);
			
			setLightIfExist(launchpad, 4, 4+i, true);
			setLightIfExist(launchpad, 5, 4+i, true);
			
			setLightIfExist(launchpad, 4, 3-i, true);
			setLightIfExist(launchpad, 5, 3-i, true);
			
			try {
				Thread.sleep(speedSelector.getValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setLightIfExist(launchpad, 4-i, 3, false);
			setLightIfExist(launchpad, 4-i, 4, false);
			
			setLightIfExist(launchpad, 5+i, 3, false);
			setLightIfExist(launchpad, 5+i, 4, false);
			
			setLightIfExist(launchpad, 4, 4+i, false);
			setLightIfExist(launchpad, 5, 4+i, false);
			
			setLightIfExist(launchpad, 4, 3-i, false);
			setLightIfExist(launchpad, 5, 3-i, false);
			
		}
		
	}
	
	private void setLightIfExist(Launchpad pad, int row, int col, boolean on){
		if(row < 0 || row > 8 || col < 0 || col > 8)
			return;
		if(on)
			pad.setLedOn(col, row, colorSelector.getValue());
		else
			pad.setLedOff(col, row);
	}
	
	@Override
	public String getName() {
		return "Exploding Cross";
	}

	@Override
	public String getDescription() {
		return null;
	}
	
	@Override
	public Option<?>[] getOptions() {
		return new Option<?>[]{speedSelector, colorSelector};
	}
	
}
