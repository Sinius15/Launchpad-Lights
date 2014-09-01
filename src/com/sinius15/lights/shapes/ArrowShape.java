package com.sinius15.lights.shapes;

import com.sinius15.launchpad.Launchpad;
import com.sinius15.lights.Shape;
import com.sinius15.lights.util.Direction;
import static com.sinius15.lights.util.Util.*;

public class ArrowShape extends Shape {
	
	@Override
	public String getName() {
		return "Arrow";
	}
	
	@Override
	public void draw(Launchpad pad, int color, int row, int colomn, Direction dir) {
		pad.setLedOn(colomn, row, color);
		switch (dir) {
			case BOT_TOP:
				setLedIfPossible(pad, row-1, colomn, color);
				setLedIfPossible(pad, row, colomn+1, color);
				setLedIfPossible(pad, row, colomn-1, color);
				break;
			case LEF_RIG:
				setLedIfPossible(pad, row+1, colomn, color);
				setLedIfPossible(pad, row-1, colomn, color);
				setLedIfPossible(pad, row, colomn+1, color);
				break;
			case RIG_LEF:
				setLedIfPossible(pad, row+1, colomn, color);
				setLedIfPossible(pad, row-1, colomn, color);
				setLedIfPossible(pad, row, colomn-1, color);
				break;
			case TOP_BOT:
				setLedIfPossible(pad, row+1, colomn, color);
				setLedIfPossible(pad, row, colomn+1, color);
				setLedIfPossible(pad, row, colomn-1, color);
				break;
		}
	}
	
}
