package com.sinius15.lights.effects;

import com.sinius15.launchpad.Launchpad;
import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Option;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.ColorOption;
import com.sinius15.lights.options.DirectionOption;
import com.sinius15.lights.options.IntOption;
import com.sinius15.lights.util.Util;

public class WaveEffect extends Effect{
	
	@Save
	public ColorOption color;
	@Save
	public IntOption speed;
	@Save
	public DirectionOption direction;
	
	public WaveEffect(int row, int colomn){
		super(row, colomn);
		color = new ColorOption();
		speed = new IntOption(30);
		direction = new DirectionOption();
	}
	
	@Override
	public String getName() {
		return "Wave";
	}

	@Override
	public String getDescription() {
		return "A wave of lights from one side of the pad to the other side.";
	}

	@Override
	public void buttonDown(OwnedLaunchpad launchpad) {
		switch (direction.getValue()) {
			case BOT_TOP:
				for(int row = 8; row >= 0; row--){
					Util.colorRow(launchpad, color.getValue(), row, UID);
					pause();
					Util.colorRow(launchpad, Launchpad.COLOR_OFF, row, UID);
				}
				break;
			case TOP_BOT:
				for(int row = 0; row <= 8; row++){
					Util.colorRow(launchpad, color.getValue(), row, UID);
					pause();
					Util.colorRow(launchpad, Launchpad.COLOR_OFF, row, UID);
				}
				break;
			case LEF_RIG:
				for(int col = 0; col <= 8; col++){
					Util.colorColomn(launchpad, color.getValue(), col, UID);
					pause();
					Util.colorColomn(launchpad, Launchpad.COLOR_OFF, col, UID);
				}
				break;
			case RIG_LEF:
				for(int col = 8; col >= 0; col--){
					Util.colorColomn(launchpad, color.getValue(), col, UID);
					pause();
					Util.colorColomn(launchpad, Launchpad.COLOR_OFF, col, UID);
				}
				break;
			default:
				break;
		}
	}
	
	@Override
	public Option<?>[] getOptions() {
		return new Option<?>[]{speed, direction, color};
	}
	
	private void pause(){
		try {
			Thread.sleep(speed.getValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
