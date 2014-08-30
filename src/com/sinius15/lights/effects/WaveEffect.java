package com.sinius15.lights.effects;

import java.awt.Color;
import java.util.Random;

import com.sinius15.launchpad.BufferedLaunchpad;
import com.sinius15.launchpad.Launchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Option;
import com.sinius15.lights.Util;
import com.sinius15.lights.options.BooleanOption;
import com.sinius15.lights.options.ColorOption;
import com.sinius15.lights.options.ComboBoxOption;
import com.sinius15.lights.options.SpeedOption;

public class WaveEffect extends Effect{
	
	private ColorOption color;
	private SpeedOption speed;
	private ComboBoxOption direction;
	private BooleanOption random;
	
	public WaveEffect(int row, int colomn){
		super(row, colomn);
		color = new ColorOption();
		speed = new SpeedOption(30);
		direction = new ComboBoxOption("What way to do stuff?", Dir.Values(), Dir.TOP_BOT.name);
		random = new BooleanOption();
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
					colorRow(row, launchpad);
					pause();
					Util.colorRow(launchpad, Launchpad.COLOR_OFF, row);
				}
				break;
			case TOP_BOT:
				for(int row = 0; row <= 8; row++){
					colorRow(row, launchpad);
					pause();
					Util.colorRow(launchpad, Launchpad.COLOR_OFF, row);
				}
				break;
			case LEF_RIG:
				for(int col = 0; col <= 8; col++){
					colorColomn(col, launchpad);
					pause();
					Util.colorColomn(launchpad, Launchpad.COLOR_OFF, col);
				}
				break;
			case RIG_LEF:
				for(int col = 8; col >= 0; col--){
					colorColomn(col, launchpad);
					pause();
					Util.colorColomn(launchpad, Launchpad.COLOR_OFF, col);
				}
				break;
			default:
				break;
		}
	}
	
	private void colorRow(int row, BufferedLaunchpad pad){
		int col = color.getValue();
		if(random.getValue())
			col = randomColor();
		Util.colorRow(pad, col, row);
	}
	
	private void colorColomn(int row, BufferedLaunchpad pad){
		int col = color.getValue();
		if(random.getValue())
			col = randomColor();
		Util.colorColomn(pad, col, row);
	}
	
	private int[] randomColors = new int[]{Launchpad.COLOR_AMBER_FULL, Launchpad.COLOR_GREEN_FULL, Launchpad.COLOR_RED_FULL,
			Launchpad.COLOR_AMBER_LOW, Launchpad.COLOR_GREEN_LOW, Launchpad.COLOR_RED_LOW
			};
	private int randomColor(){
		return randomColors[new Random().nextInt(randomColors.length)];
	}
	
	@Override
	public Option<?>[] getOptions() {
		return new Option<?>[]{color, speed, direction, random};
	}
	
	@Override
	public void buttonUp(BufferedLaunchpad launchpad) {
		//do nothing
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
