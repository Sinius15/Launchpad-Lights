package com.sinius15.lights.shapes;

import static com.sinius15.lights.util.Util.setLedIfPossible;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Shape;
import com.sinius15.lights.util.Direction;

public class ArrowShape extends Shape {
	
	@Override
	public String getName() {
		return "Arrow";
	}
	
	@Override
	public void draw(OwnedLaunchpad pad, int color, int row, int colomn, Direction dir, String owner) {
		pad.setLedOn(colomn, row, color, owner);
		switch (dir) {
			case BOT_TOP:
				setLedIfPossible(pad, row-1, colomn, color, owner);
				setLedIfPossible(pad, row, colomn+1, color, owner);
				setLedIfPossible(pad, row, colomn-1, color, owner);
				break;
			case LEF_RIG:
				setLedIfPossible(pad, row+1, colomn, color, owner);
				setLedIfPossible(pad, row-1, colomn, color, owner);
				setLedIfPossible(pad, row, colomn+1, color, owner);
				break;
			case RIG_LEF:
				setLedIfPossible(pad, row+1, colomn, color, owner);
				setLedIfPossible(pad, row-1, colomn, color, owner);
				setLedIfPossible(pad, row, colomn-1, color, owner);
				break;
			case TOP_BOT:
				setLedIfPossible(pad, row+1, colomn, color, owner);
				setLedIfPossible(pad, row, colomn+1, color, owner);
				setLedIfPossible(pad, row, colomn-1, color, owner);
				break;
		}
	}
	
}
