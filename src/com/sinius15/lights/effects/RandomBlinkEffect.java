package com.sinius15.lights.effects;

import java.util.Random;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Option;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.ColorOption;
import com.sinius15.lights.options.IntOption;

public class RandomBlinkEffect extends Effect {
	
	@Save
	public ColorOption colorSelector;
	@Save
	public IntOption speed;
	@Save
	public IntOption persentage;
	
	private boolean running = false;
	private boolean[][] lights = new boolean[9][9];
	
	public RandomBlinkEffect(OwnedLaunchpad pad, int row, int colomn) {
		super(pad, row, colomn);
		speed = new IntOption(5);
		persentage = new IntOption(50);
		persentage.setTitle("Persentage filled: ");
		persentage.setMin(0);
		persentage.setMax(100);
		colorSelector = new ColorOption();
	}
	
	@Override
	public void buttonDown() {
		running = true;
		do {
			paintLights(false); // turn off old lights
			randomizeLights(); // get new lights
			paintLights(true); // turn on new lights
			try {
				Thread.sleep(speed.getValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (running);
		paintLights(false);
	}
	
	private void paintLights(boolean on) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (lights[row][col]) {
					if (on)
						setLedOn(col, row, colorSelector.getValue());
					else
						setLedOff(col, row);
				}
			}
		}
	}
	
	private void randomizeLights() {
		Random r = new Random();
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				int randomNum = r.nextInt((100 - 1) + 1) + 1;
				if (randomNum <= persentage.getValue())
					lights[row][col] = true;
				else
					lights[row][col] = false;
			}
		}
	}
	
	@Override
	public void buttonUp() {
		running = false;
	}
	
	@Override
	public String getName() {
		return "random blinking";
	}
	
	@Override
	public String getDescription() {
		return null;
	}
	
	@Override
	public Option<?>[] getOptions() {
		return new Option<?>[] { speed, persentage, colorSelector };
	}
	
}
