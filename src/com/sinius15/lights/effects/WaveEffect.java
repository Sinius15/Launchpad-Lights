package com.sinius15.lights.effects;

import com.sinius15.launchpad.BufferedLaunchpad;
import com.sinius15.launchpad.Launchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Util;

public class WaveEffect extends Effect{
	
	private int color;
	private int speed;
	private Dir direction;
	
	public WaveEffect(int row, int colomn, String saveData){
		super(row, colomn, saveData);
		if(saveData == null){//use default vallues;
			color = Launchpad.COLOR_GREEN_FULL;
			speed = 40;
			direction = Dir.BOT_TOP;
		}else{
			String[] split = saveData.split(";");
			color = Integer.valueOf(split[0]);
			direction = Dir.values()[Integer.valueOf(split[1])];
			speed = Integer.valueOf(split[2]);	
		}
		
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
		switch (direction) {
			case BOT_TOP:
				for(int row = 8; row >= 0; row--){
					Util.colorRow(launchpad, color, row);
					pause(speed);
					Util.colorRow(launchpad, Launchpad.COLOR_OFF, row);
				}
				break;
			case TOP_BOT:
				for(int row = 0; row <= 8; row++){
					Util.colorRow(launchpad, color, row);
					pause(speed);
					Util.colorRow(launchpad, Launchpad.COLOR_OFF, row);
				}
				break;
			case LEF_RIG:
				for(int col = 0; col <= 8; col++){
					Util.colorColomn(launchpad, color, col);
					pause(speed);
					Util.colorColomn(launchpad, Launchpad.COLOR_OFF, col);
				}
				break;
			case RIG_LEF:
				for(int col = 8; col >= 0; col--){
					Util.colorColomn(launchpad, color, col);
					pause(speed);
					Util.colorColomn(launchpad, Launchpad.COLOR_OFF, col);
				}
				break;
			default:
				break;
		}
	}
	
	@Override
	public void buttonUp(BufferedLaunchpad launchpad) {
		//do nothing
	}
	
	private void pause(int ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getSaveData() {
		return color + ";" + direction.index + ";" + speed;
	}
	
	private enum Dir{
		TOP_BOT("Top to Bottom", 0), BOT_TOP("Bottom to Top", 1), LEF_RIG("Left to Right", 2), RIG_LEF("Right to Left", 3);
		
		public String name;
		public int index;
		
		Dir(String name, int index){
			this.name = name;
			this.index = index;
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
		
	}
	
}
