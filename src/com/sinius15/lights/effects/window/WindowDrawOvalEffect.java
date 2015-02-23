package com.sinius15.lights.effects.window;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.IntOption;
import com.sinius15.lights.options.PointOption;
import com.sinius15.lights.options.RGBOption;
import com.sinius15.lights.options.ScreenSelectOption;

public class WindowDrawOvalEffect extends Effect {

	@Save
	public ScreenSelectOption screenSelector;
	
	@Save
	public RGBOption onColor;
	
	@Save
	public RGBOption offColor;
	
	@Save
	public PointOption location;
	
	@Save
	public IntOption radius;
	
	public WindowDrawOvalEffect(OwnedLaunchpad pad, int row, int colomn) {
		super(pad, row, colomn);
		this.useAdvancedLight = null;
		
		this.screenSelector = new ScreenSelectOption();
		this.onColor = new RGBOption("Button press color:");
		this.offColor = new RGBOption("Button release color:");
		this.location = new PointOption("location:", PointOption.TYPE_SCREEN);
		this.radius = new IntOption(50);
		this.radius.setTitle("Radius:");
		
	}

	@Override
	public String getName() {
		return "Window draw Oval";
	}

	@Override
	public String getDescription() {
		return "Draws a oval on the computer screen.";
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
		g.fillOval(location.getValue().x, location.getValue().y, radius.getValue(), radius.getValue());
		WindowManager.repaintScreens();
	}

}
