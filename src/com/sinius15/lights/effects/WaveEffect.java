package com.sinius15.lights.effects;

import com.sinius15.launchpad.Launchpad;
import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.ColorOption;
import com.sinius15.lights.options.DirectionOption;
import com.sinius15.lights.options.IntOption;

public class WaveEffect extends Effect{
	
	@Save
	public ColorOption color;
	@Save
	public IntOption speed;
	@Save
	public DirectionOption direction;
	
	public WaveEffect(OwnedLaunchpad pad, int row, int colomn) {
		super(pad, row, colomn);
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
	public void buttonDown() {
		switch (direction.getValue()) {
			case BOT_TOP:
				for(int row = 8; row >= 0; row--){
					colorRow(color.getValue(), row);
					pause();
					colorRow(Launchpad.COLOR_OFF, row);
				}
				break;
			case TOP_BOT:
				for(int row = 0; row <= 8; row++){
					colorRow(color.getValue(), row);
					pause();
					colorRow(Launchpad.COLOR_OFF, row);
				}
				break;
			case LEF_RIG:
				for(int col = 0; col <= 8; col++){
					colorColumn(color.getValue(), col);
					pause();
					colorColumn(Launchpad.COLOR_OFF, col);
				}
				break;
			case RIG_LEF:
				for(int col = 8; col >= 0; col--){
					colorColumn(color.getValue(), col);
					pause();
					colorColumn(Launchpad.COLOR_OFF, col);
				}
				break;
			default:
				break;
		}
	}
	
	private void pause(){
		try {
			Thread.sleep(speed.getValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
