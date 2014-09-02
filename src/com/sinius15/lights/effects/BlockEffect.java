package com.sinius15.lights.effects;

import java.awt.Point;

import com.sinius15.launchpad.BufferedLaunchpad;
import com.sinius15.launchpad.Launchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Option;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.BooleanOption;
import com.sinius15.lights.options.ColorOption;
import com.sinius15.lights.options.KeepAliveOption;
import com.sinius15.lights.options.PointOption;

public class BlockEffect extends Effect {
	
	private static final long serialVersionUID = 4043950466106633964L;
	
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
	
	private void showLights(Launchpad pad){
		Point p1 = leftTopCorner.getValue();
		Point p2 = rightBottomCorner.getValue();
		if(showOutline.getValue()){
			for(int row = p1.x; row <= p2.x; row++){
				pad.setLedOn(p1.y, row, colorSelector.getValue());
				pad.setLedOn(p2.y, row, colorSelector.getValue());
			}
			for(int col = p1.y; col <= p2.y; col++){
				pad.setLedOn(col, p1.x, colorSelector.getValue());
				pad.setLedOn(col, p2.x, colorSelector.getValue());
			}
		}else{
			for(int row = p1.x; row <= p2.x; row++){
				for(int col = p1.y; col <= p2.y; col++){
					pad.setLedOn(col, row, colorSelector.getValue());
				}
			}	
		}
	}
	
	private void removeLight(Launchpad pad){
		Point p1 = leftTopCorner.getValue();
		Point p2 = rightBottomCorner.getValue();
		
		if(showOutline.getValue()){
			for(int row = p1.x; row <= p2.x; row++){
				pad.setLedOff(p1.y, row);
				pad.setLedOff(p2.y, row);
			}
			for(int col = p1.y; col <= p2.y; col++){
				pad.setLedOff(col, p1.x);
				pad.setLedOff(col, p2.x);
			}
		}else{
			for(int row = p1.x; row <= p2.x; row++){
				for(int col = p1.y; col <= p2.y; col++){
					pad.setLedOff(col, row);
				}
			}	
		}
	}
	
	@Override
	public Option<?>[] getOptions() {
		return new Option<?>[]{leftTopCorner, rightBottomCorner,showOutline, aliveOption, colorSelector};
	}
}
