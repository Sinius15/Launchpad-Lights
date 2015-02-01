package com.sinius15.lights.effects;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;

public class NoneEffect extends Effect{

	public NoneEffect(OwnedLaunchpad pad, int row, int colomn) {
		super(pad, row, colomn);
	}

	@Override
	public String getName() {
		return "None";
	}

	@Override
	public String getDescription() {
		return "No effect at this button.";
	}

}
