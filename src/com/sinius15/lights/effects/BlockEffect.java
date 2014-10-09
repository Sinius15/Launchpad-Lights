package com.sinius15.lights.effects;

import java.awt.Point;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Option;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.BooleanOption;
import com.sinius15.lights.options.ColorOption;
import com.sinius15.lights.options.KeepAliveOption;
import com.sinius15.lights.options.PointOption;

public class BlockEffect extends Effect {
	
	@Save
	public PointOption leftTopCorner, rightBottomCorner;
	@Save
	public ColorOption colorSelector;
	@Save
	public KeepAliveOption aliveOption;
	@Save
	public BooleanOption showOutline;
	
	private boolean isButtonDown = false, removeLampsOnButtonUp = false;
	
	public BlockEffect(int row, int colomn) {
		super(row, colomn);
		leftTopCorner = new PointOption("Left top corner of the block:");
		rightBottomCorner = new PointOption("Right bottom corner of the block:");
		colorSelector = new ColorOption();
		aliveOption = new KeepAliveOption();
		showOutline = new BooleanOption("Show only outline?", false);
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
	public void buttonDown(OwnedLaunchpad launchpad) {
		isButtonDown = true;
		showLights(launchpad);
		if (aliveOption.getValue() != -1) {
			removeLampsOnButtonUp = false;
			try {
				Thread.sleep(aliveOption.getValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (isButtonDown)
				removeLampsOnButtonUp = true;
			else
				removeLight(launchpad);
		} else {
			removeLampsOnButtonUp = true;
		}
		
	}
	
	@Override
	public void buttonUp(OwnedLaunchpad launchpad) {
		isButtonDown = false;
		if (removeLampsOnButtonUp) {
			removeLight(launchpad);
		}
		
	}
	
	private void showLights(OwnedLaunchpad pad) {
		Point p1 = leftTopCorner.getValue();
		Point p2 = rightBottomCorner.getValue();
		if (showOutline.getValue()) {
			for (int row = p1.x; row <= p2.x; row++) {
				pad.setLedOn(p1.y, row, colorSelector.getValue(), UID);
				pad.setLedOn(p2.y, row, colorSelector.getValue(), UID);
			}
			for (int col = p1.y; col <= p2.y; col++) {
				pad.setLedOn(col, p1.x, colorSelector.getValue(), UID);
				pad.setLedOn(col, p2.x, colorSelector.getValue(), UID);
			}
		} else {
			for (int row = p1.x; row <= p2.x; row++) {
				for (int col = p1.y; col <= p2.y; col++) {
					pad.setLedOn(col, row, colorSelector.getValue(), UID);
				}
			}
		}
	}
	
	private void removeLight(OwnedLaunchpad pad) {
		Point p1 = leftTopCorner.getValue();
		Point p2 = rightBottomCorner.getValue();
		
		if (showOutline.getValue()) {
			for (int row = p1.x; row <= p2.x; row++) {
				pad.setLedOff(p1.y, row, UID);
				pad.setLedOff(p2.y, row, UID);
			}
			for (int col = p1.y; col <= p2.y; col++) {
				pad.setLedOff(col, p1.x, UID);
				pad.setLedOff(col, p2.x, UID);
			}
		} else {
			for (int row = p1.x; row <= p2.x; row++) {
				for (int col = p1.y; col <= p2.y; col++) {
					pad.setLedOff(col, row, UID);
				}
			}
		}
	}
	
	@Override
	public Option<?>[] getOptions() {
		return new Option<?>[] { leftTopCorner, rightBottomCorner, showOutline, aliveOption,
				colorSelector };
	}
}
