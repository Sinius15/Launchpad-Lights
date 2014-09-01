package com.sinius15.lights.shapes;

import com.sinius15.launchpad.Launchpad;
import com.sinius15.lights.Shape;
import com.sinius15.lights.util.Direction;
import static com.sinius15.lights.util.Util.*;

public class CrossShape extends Shape{
	
	@Override
	public String getName() {
		return "Cross";
	}

	@Override
	public void draw(Launchpad pad, int color, int row, int colomn, Direction dir) {
		setLedIfPossible(pad, row, colomn, color);
		setLedIfPossible(pad, row+1, colomn, color);
		setLedIfPossible(pad, row-1, colomn, color);
		setLedIfPossible(pad, row, colomn+1, color);
		setLedIfPossible(pad, row, colomn-1, color);
	}
	
}
