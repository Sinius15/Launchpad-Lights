package com.sinius15.lights.effects;

import com.sinius15.launchpad.BufferedLaunchpad;
import com.sinius15.launchpad.Launchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Option;
import com.sinius15.lights.options.ColorOption;
import com.sinius15.lights.options.KeepAliveOption;

public class StarEffect extends Effect {
	
	private ColorOption color;
	private KeepAliveOption alave;
	
	private boolean isPressed = false, shouldReleaseOnButtonUp = false;
	
	public StarEffect(int row, int colomn){
		super(row, colomn);
		color = new ColorOption();
		alave = new KeepAliveOption();
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
		isPressed = true;
		showPattern(launchpad);
		if (alave.getValue() >= 0) {
			shouldReleaseOnButtonUp = false;
				
			try {
				Thread.sleep(alave.getValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(isPressed)
				shouldReleaseOnButtonUp = true;
			else
				removePattern(launchpad);
		}else{
			shouldReleaseOnButtonUp = true;
		}
	}
	
	@Override
	public void buttonUp(BufferedLaunchpad launchpad) {
		isPressed = false;
		if(shouldReleaseOnButtonUp){
			removePattern(launchpad);
			shouldReleaseOnButtonUp = false;
		}
	}
	
	private void showPattern(Launchpad launchpad){
		setLightIfExist(launchpad, row, colomn, true);
		setLightIfExist(launchpad, row+1, colomn, true);
		setLightIfExist(launchpad, row-1, colomn, true);
		setLightIfExist(launchpad, row, colomn+1, true);
		setLightIfExist(launchpad, row, colomn-1, true);
	}
	
	private void removePattern(Launchpad launchpad){
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
			pad.setLedOn(col, row, color.getValue());
		else
			pad.setLedOff(col, row);
	}
	
	@Override
	public Option<?>[] getOptions() {
		return new Option<?>[]{alave, color};
	}
	
}
