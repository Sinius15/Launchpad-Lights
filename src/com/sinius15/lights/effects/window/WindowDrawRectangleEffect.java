package com.sinius15.lights.effects.window;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.PointOption;
import com.sinius15.lights.options.RGBOption;
import com.sinius15.lights.options.ScreenSelectOption;

public class WindowDrawRectangleEffect extends Effect {

	@Save
	public ScreenSelectOption screenSelector;
	@Save
	public PointOption leftTopPoint, rightBottomPoint;
	@Save
	public RGBOption onColor;
	@Save
	public RGBOption offColor;

	public WindowDrawRectangleEffect(OwnedLaunchpad pad, int row, int colomn) {
		super(pad, row, colomn);
		screenSelector = new ScreenSelectOption();
		leftTopPoint = new PointOption("Left top corner of block:", PointOption.TYPE_SCREEN);
		rightBottomPoint = new PointOption("Right bottom corner of block:", PointOption.TYPE_SCREEN);
		onColor = new RGBOption("Color of block when button pressed:");
		offColor = new RGBOption("Color of block when button released:");

		this.useAdvancedLight = null;
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
		draw(onColor.getValue());
	}

	@Override
	public void buttonUp() {
		draw(offColor.getValue());
	}
	
	private void draw(Color color){
		Graphics2D g = WindowManager.getDrawGraphics(screenSelector.getValue());
		if (g == null)
			return;
		g.setColor(color);
		g.fillRect(leftTopPoint.getValue().x, leftTopPoint.getValue().y,
				rightBottomPoint.getValue().x - leftTopPoint.getValue().x,
				rightBottomPoint.getValue().y - leftTopPoint.getValue().y);
		WindowManager.repaintScreens();
	}

}
