package com.sinius15.lights.effects.window;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.FontOption;
import com.sinius15.lights.options.PointOption;
import com.sinius15.lights.options.RGBOption;
import com.sinius15.lights.options.ScreenSelectOption;
import com.sinius15.lights.options.StringOption;

public class WindowDrawTextEffect extends Effect {

	@Save
	public ScreenSelectOption screenSelector;
	@Save
	public PointOption position;
	@Save
	public RGBOption onColor;
	@Save
	public RGBOption offColor;
	@Save
	public StringOption text;
	@Save
	public FontOption font;

	public WindowDrawTextEffect(OwnedLaunchpad pad, int row, int colomn) {
		super(pad, row, colomn);
		screenSelector = new ScreenSelectOption();
		position = new PointOption("Position of the text:", PointOption.TYPE_SCREEN);
		onColor = new RGBOption("Color of text when button pressed:");
		offColor = new RGBOption("Color of text when button released:");
		text = new StringOption("Enter the text to show: ");
		font = new FontOption("Select font of the text:");

		this.useAdvancedLight = null;
	}

	@Override
	public String getName() {
		return "Window draw text";
	}

	@Override
	public String getDescription() {
		return "Draws a string on a screen.";
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
		g.setFont(font.getValue());
		g.drawString(text.getValue(), position.getValue().x, position.getValue().y);
		WindowManager.repaintScreens();
	}

}