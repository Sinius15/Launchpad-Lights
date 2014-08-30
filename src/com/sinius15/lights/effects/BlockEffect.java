package com.sinius15.lights.effects;

import java.awt.Point;

import com.sinius15.launchpad.BufferedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Option;
import com.sinius15.lights.options.ColorOption;
import com.sinius15.lights.options.PointOption;

public class BlockEffect extends Effect {
	
	PointOption leftTopCorner, rightBottomCorner;
	ColorOption colorSelector;
	
	public BlockEffect(int row, int colomn) {
		super(row, colomn);
		leftTopCorner = new PointOption("Left top corner of the block:");
		rightBottomCorner = new PointOption("Right bottom corner of the block:");
		colorSelector = new ColorOption();
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
		Point p1 = leftTopCorner.getValue();
		Point p2 = rightBottomCorner.getValue();
		
		for(int row = p1.x; row <= p2.x; row++){
			for(int col = p1.y; col <= p2.y; col++){
				launchpad.setLedOn(col, row, colorSelector.getValue());
			}
		}
	}
	
	@Override
	public void buttonUp(BufferedLaunchpad launchpad) {
		Point p1 = leftTopCorner.getValue();
		Point p2 = rightBottomCorner.getValue();
		
		for(int row = p1.x; row <= p2.x; row++){
			for(int col = p1.y; col <= p2.y; col++){
				launchpad.setLedOff(col, row);
			}
		}
	}
	
	@Override
	public Option<?>[] getOptions() {
		return new Option<?>[]{leftTopCorner, rightBottomCorner, colorSelector};
	}
}
