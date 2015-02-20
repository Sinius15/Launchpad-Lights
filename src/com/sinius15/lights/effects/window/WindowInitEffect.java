package com.sinius15.lights.effects.window;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;

public class WindowInitEffect extends Effect{

	public WindowInitEffect(OwnedLaunchpad pad, int row, int colomn) {
		super(pad, row, colomn);
	}

	@Override
	public String getName() {
		return "Initalize Window";
	}

	@Override
	public String getDescription() {
		return "initializes the display.";
	}

}
