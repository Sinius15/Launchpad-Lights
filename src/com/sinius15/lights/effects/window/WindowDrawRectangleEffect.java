package com.sinius15.lights.effects.window;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.ScreenSelectOption;

public class WindowDrawRectangleEffect extends Effect{

	@Save
	public ScreenSelectOption screenSelector;

	public WindowDrawRectangleEffect(OwnedLaunchpad pad, int row, int colomn) {
		super(pad, row, colomn);
		screenSelector = new ScreenSelectOption();
	}

	@Override
	public String getName() {
		return "Window draw rectangle";
	}

	@Override
	public String getDescription() {
		return "Draws a rectangle on a screen.";
	}

	@Override
	public void buttonDown() {
		Graphics2D g = WindowManager.getDrawPane(screenSelector.getValue());
		if(g == null)
			return;
		g.setColor(Color.white);
		g.fillRect(0, 0, 500, 500);
	}

	@Override
	public void buttonUp() {
		Graphics2D g = WindowManager.getDrawPane(screenSelector.getValue());
		if(g == null)
			return;
		g.setColor(Color.black);
		g.fillRect(0, 0, 500, 500);
	}

}
