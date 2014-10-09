package com.sinius15.lights.shapes;

import static com.sinius15.lights.util.Util.setLedIfPossible;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Shape;
import com.sinius15.lights.util.Direction;

public class CrossShape extends Shape{
	
	@Override
	public String getName() {
		return "Cross";
	}

	@Override
	public void draw(OwnedLaunchpad pad, int color, int row, int colomn, Direction dir, String owner) {
		setLedIfPossible(pad, row, colomn, color, owner);
		setLedIfPossible(pad, row+1, colomn, color, owner);
		setLedIfPossible(pad, row-1, colomn, color, owner);
		setLedIfPossible(pad, row, colomn+1, color, owner);
		setLedIfPossible(pad, row, colomn-1, color, owner);
	}
	
}
