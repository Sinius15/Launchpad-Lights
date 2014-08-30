package com.sinius15.lights.effects;

import com.sinius15.launchpad.BufferedLaunchpad;
import com.sinius15.launchpad.Launchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Option;
import com.sinius15.lights.Util;
import com.sinius15.lights.options.ColorOption;
import com.sinius15.lights.options.ComboBoxOption;
import com.sinius15.lights.options.SpeedOption;

public class WaveEffect extends Effect{
	
	private ColorOption color;
	private SpeedOption speed;
	private ComboBoxOption direction;
	
	public WaveEffect(int row, int colomn){
		super(row, colomn);
		color = new ColorOption();
		speed = new SpeedOption(30);
		direction = new ComboBoxOption("What way to do stuff?", Dir.Values(), Dir.TOP_BOT.name);
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
	public void buttonDown(BufferedLaunchpad launchpad) {
		switch (Dir.fromString(direction.getValue())) {
			case BOT_TOP:
				for(int row = 8; row >= 0; row--){
					Util.colorRow(launchpad, color.getValue(), row);
					pause();
					Util.colorRow(launchpad, Launchpad.COLOR_OFF, row);
				}
				break;
			case TOP_BOT:
				for(int row = 0; row <= 8; row++){
					Util.colorRow(launchpad, color.getValue(), row);
					pause();
					Util.colorRow(launchpad, Launchpad.COLOR_OFF, row);
				}
				break;
			case LEF_RIG:
				for(int col = 0; col <= 8; col++){
					Util.colorColomn(launchpad, color.getValue(), col);
					pause();
					Util.colorColomn(launchpad, Launchpad.COLOR_OFF, col);
				}
				break;
			case RIG_LEF:
				for(int col = 8; col >= 0; col--){
					Util.colorColomn(launchpad, color.getValue(), col);
					pause();
					Util.colorColomn(launchpad, Launchpad.COLOR_OFF, col);
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
	
	private enum Dir{
		TOP_BOT("Top to Bottom"), BOT_TOP("Bottom to Top"), LEF_RIG("Left to Right"), RIG_LEF("Right to Left");
		
		public String name;
		
		Dir(String name){
			this.name = name;
		}
		
		public String toString(){
			return name;
		}
		
		public static Dir fromString(String inName){
			for(Dir d : Dir.values()){
				if(d.name.equals(inName))
					return d;
			}
			return null;
		}
		
		public static String[] Values(){
			return new String[]{TOP_BOT.name, BOT_TOP.name, LEF_RIG.name, RIG_LEF.name};
		}
	}
	
}
