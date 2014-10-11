package com.sinius15.lights.effects;

import java.awt.Point;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
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
	
	public BlockEffect(OwnedLaunchpad pad, int row, int colomn) {
		super(pad, row, colomn);
		leftTopCorner = new PointOption("Left top corner of the block:");
		rightBottomCorner = new PointOption("Right bottom corner of the block:");
		colorSelector = new ColorOption();
		aliveOption = new KeepAliveOption();
		showOutline = new BooleanOption("Show only outline?", false);
		
		options.add(leftTopCorner);
		options.add(rightBottomCorner);
		options.add(showOutline);
		options.add(aliveOption);
		options.add(colorSelector);
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
	public void buttonDown() {
		isButtonDown = true;
		showLights();
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
				removeLight();
		} else {
			removeLampsOnButtonUp = true;
		}
		
	}
	
	@Override
	public void buttonUp() {
		isButtonDown = false;
		if (removeLampsOnButtonUp) {
			removeLight();
		}
		
	}
	
	private void showLights() {
		Point p1 = leftTopCorner.getValue();
		Point p2 = rightBottomCorner.getValue();
		if (showOutline.getValue()) {
			for (int row = p1.x; row <= p2.x; row++) {
				setLedOn(p1.y, row, colorSelector.getValue());
				setLedOn(p2.y, row, colorSelector.getValue());
			}
			for (int col = p1.y; col <= p2.y; col++) {
				setLedOn(col, p1.x, colorSelector.getValue());
				setLedOn(col, p2.x, colorSelector.getValue());
			}
		} else {
			for (int row = p1.x; row <= p2.x; row++) {
				for (int col = p1.y; col <= p2.y; col++) {
					setLedOn(col, row, colorSelector.getValue());
				}
			}
		}
	}
	
	private void removeLight() {
		Point p1 = leftTopCorner.getValue();
		Point p2 = rightBottomCorner.getValue();
		
		if (showOutline.getValue()) {
			for (int row = p1.x; row <= p2.x; row++) {
				setLedOff(p1.y, row);
				setLedOff(p2.y, row);
			}
			for (int col = p1.y; col <= p2.y; col++) {
				setLedOff(col, p1.x);
				setLedOff(col, p2.x);
			}
		} else {
			for (int row = p1.x; row <= p2.x; row++) {
				for (int col = p1.y; col <= p2.y; col++) {
					setLedOff(col, row);
				}
			}
		}
	}
}
