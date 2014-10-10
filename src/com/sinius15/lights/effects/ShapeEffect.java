package com.sinius15.lights.effects;

import com.sinius15.launchpad.Launchpad;
import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Option;
import com.sinius15.lights.Save;
import com.sinius15.lights.Shape;
import com.sinius15.lights.options.ColorOption;
import com.sinius15.lights.options.ComboBoxOption;
import com.sinius15.lights.options.KeepAliveOption;
import com.sinius15.lights.util.Direction;

public class ShapeEffect extends Effect {
	
	@Save
	public ColorOption colorChooser;
	@Save
	public KeepAliveOption alave;
	@Save
	public ComboBoxOption shapeChooser;
	
	private boolean isPressed = false, shouldReleaseOnButtonUp = false;
	
	public ShapeEffect(OwnedLaunchpad pad, int row, int colomn) {
		super(pad, row, colomn);
		colorChooser = new ColorOption();
		alave = new KeepAliveOption();
		shapeChooser = new ComboBoxOption("Shape", Shape.shapeStrings, Shape.shapes[0].getName());
	}
	
	@Override
	public String getName() {
		return "Pattern Effect";
	}
	
	@Override
	public String getDescription() {
		return "Shows a star around the place where you press the button.";
	}
	
	@Override
	public void buttonDown() {
		isPressed = true;
		showPattern();
		if (alave.getValue() >= 0) {
			shouldReleaseOnButtonUp = false;
			
			try {
				Thread.sleep(alave.getValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (isPressed)
				shouldReleaseOnButtonUp = true;
			else
				removePattern();
		} else {
			shouldReleaseOnButtonUp = true;
		}
	}
	
	@Override
	public void buttonUp() {
		isPressed = false;
		if (shouldReleaseOnButtonUp) {
			removePattern();
			shouldReleaseOnButtonUp = false;
		}
	}
	
	private void showPattern() {
		Shape.stringToShape(shapeChooser.getValue()).draw(this, colorChooser.getValue(), row,
				column, Direction.BOT_TOP);
	}
	
	private void removePattern() {
		Shape.stringToShape(shapeChooser.getValue()).draw(this, Launchpad.COLOR_OFF, row,
				column, Direction.BOT_TOP);
	}
	
	@Override
	public Option<?>[] getOptions() {
		return new Option<?>[] { shapeChooser, alave, colorChooser };
	}
	
}
