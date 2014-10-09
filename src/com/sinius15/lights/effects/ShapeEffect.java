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
	
	public ShapeEffect(int row, int colomn) {
		super(row, colomn);
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
	public void buttonDown(OwnedLaunchpad launchpad) {
		isPressed = true;
		showPattern(launchpad);
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
				removePattern(launchpad);
		} else {
			shouldReleaseOnButtonUp = true;
		}
	}
	
	@Override
	public void buttonUp(OwnedLaunchpad launchpad) {
		isPressed = false;
		if (shouldReleaseOnButtonUp) {
			removePattern(launchpad);
			shouldReleaseOnButtonUp = false;
		}
	}
	
	private void showPattern(OwnedLaunchpad launchpad) {
		Shape.stringToShape(shapeChooser.getValue()).draw(launchpad, colorChooser.getValue(), row,
				colomn, Direction.BOT_TOP, UID);
	}
	
	private void removePattern(OwnedLaunchpad launchpad) {
		Shape.stringToShape(shapeChooser.getValue()).draw(launchpad, Launchpad.COLOR_OFF, row,
				colomn, Direction.BOT_TOP, UID);
	}
	
	@Override
	public Option<?>[] getOptions() {
		return new Option<?>[] { shapeChooser, alave, colorChooser };
	}
	
}
