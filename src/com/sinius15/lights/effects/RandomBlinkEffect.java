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
	
	public RandomBlinkEffect(int row, int colomn) {
		super(row, colomn);
		speed = new IntOption(5);
		persentage = new IntOption(50);
		persentage.setTitle("Persentage filled: ");
		persentage.setMin(0);
		persentage.setMax(100);
		colorSelector = new ColorOption();
	}
	
	@Override
	public void buttonDown(OwnedLaunchpad launchpad) {
		running = true;
		do {
			paintLights(launchpad, false); // turn off old lights
			randomizeLights(); // get new lights
			paintLights(launchpad, true); // turn on new lights
			try {
				Thread.sleep(speed.getValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (running);
		paintLights(launchpad, false);
	}
	
	private void paintLights(OwnedLaunchpad pad, boolean on) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (lights[row][col]) {
					if (on)
						pad.setLedOn(col, row, colorSelector.getValue(), UID);
					else
						pad.setLedOff(col, row, UID);
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
	public void buttonUp(OwnedLaunchpad launchpad) {
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
