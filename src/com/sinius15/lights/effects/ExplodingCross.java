package com.sinius15.lights.effects;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Option;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.ColorOption;
import com.sinius15.lights.options.IntOption;

public class ExplodingCross extends Effect{
	
	@Save
	public ColorOption colorSelector;
	@Save
	public IntOption speedSelector;
	
	public ExplodingCross(OwnedLaunchpad pad, int row, int colomn) {
		super(pad, row, colomn);
		colorSelector = new ColorOption();
		speedSelector = new IntOption(300);
	}
	
	@Override
	public void buttonDown() {
		for(int i = 0; i < 5; i++){
			setLightIfExist(4-i, 3, true);
			setLightIfExist(4-i, 4, true);
			
			setLightIfExist(5+i, 3, true);
			setLightIfExist(5+i, 4, true);
			
			setLightIfExist(4, 4+i, true);
			setLightIfExist(5, 4+i, true);
			
			setLightIfExist(4, 3-i, true);
			setLightIfExist(5, 3-i, true);
			
			try {
				Thread.sleep(speedSelector.getValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setLightIfExist(4-i, 3, false);
			setLightIfExist(4-i, 4, false);
			
			setLightIfExist(5+i, 3, false);
			setLightIfExist(5+i, 4, false);
			
			setLightIfExist(4, 4+i, false);
			setLightIfExist(5, 4+i, false);
			
			setLightIfExist(4, 3-i, false);
			setLightIfExist(5, 3-i, false);
			
		}
		
	}
	
	private void setLightIfExist( int row, int col, boolean on){
		if(row < 0 || row > 8 || col < 0 || col > 8)
			return;
		if(on)
			setLedOn(col, row, colorSelector.getValue());
		else
			setLedOff(col, row);
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
