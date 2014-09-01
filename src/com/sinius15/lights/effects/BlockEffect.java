package com.sinius15.lights.effects;

import java.awt.Point;

import com.sinius15.launchpad.BufferedLaunchpad;
import com.sinius15.launchpad.Launchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Option;
import com.sinius15.lights.options.ColorOption;
import com.sinius15.lights.options.KeepAliveOption;
import com.sinius15.lights.options.PointOption;

public class BlockEffect extends Effect {
	
	private PointOption leftTopCorner, rightBottomCorner;
	private ColorOption colorSelector;
	private KeepAliveOption aliveOption;
	
	private boolean isButtonDown = false, removeLampsOnButtonUp = false;
	
	public BlockEffect(int row, int colomn) {
		super(row, colomn);
		leftTopCorner = new PointOption("Left top corner of the block:");
		rightBottomCorner = new PointOption("Right bottom corner of the block:");
		colorSelector = new ColorOption();
		aliveOption = new KeepAliveOption();
	}

	@Override
	public String getName() {
		return "Block";
	}
	
	@Override
	public String getDescription() {
		return null;
	}
	
	@Override
	public void buttonDown(BufferedLaunchpad launchpad) {
		isButtonDown = true;
		showLights(launchpad);
		if(aliveOption.getValue() != -1){
			removeLampsOnButtonUp = false;
			try {
				Thread.sleep(aliveOption.getValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(isButtonDown)
				removeLampsOnButtonUp = true;
			else
				removeLight(launchpad);
		}else{
			removeLampsOnButtonUp = true;
		}
		
	}
	
	@Override
	public void buttonUp(BufferedLaunchpad launchpad) {
		isButtonDown = false;
		if(removeLampsOnButtonUp){
			removeLight(launchpad);
		}
		
	}
	
	private void removeLight(Launchpad pad){
		Point p1 = leftTopCorner.getValue();
		Point p2 = rightBottomCorner.getValue();
		
		for(int row = p1.x; row <= p2.x; row++){
			for(int col = p1.y; col <= p2.y; col++){
				pad.setLedOff(col, row);
			}
		}
	}
	private void showLights(Launchpad pad){
		Point p1 = leftTopCorner.getValue();
		Point p2 = rightBottomCorner.getValue();
		
		for(int row = p1.x; row <= p2.x; row++){
			for(int col = p1.y; col <= p2.y; col++){
				pad.setLedOn(col, row, colorSelector.getValue());
			}
		}
	}
	
	
	@Override
	public Option<?>[] getOptions() {
		return new Option<?>[]{aliveOption, leftTopCorner, rightBottomCorner, colorSelector};
	}
}
