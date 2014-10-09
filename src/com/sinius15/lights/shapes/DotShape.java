package com.sinius15.lights.shapes;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Shape;
import com.sinius15.lights.util.Direction;

public class DotShape extends Shape{

	@Override
	public String getName() {
		return "Dot";
	}

	@Override
	public void draw(OwnedLaunchpad pad, int color, int row, int colomn, Direction dir, String owner) {
		pad.setLedOn(colomn, row, color, owner);
	}
	
}
