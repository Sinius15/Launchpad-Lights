package com.sinius15.lights.shapes;

import com.sinius15.lights.Effect;
import com.sinius15.lights.Shape;
import com.sinius15.lights.util.Direction;

public class CrossShape extends Shape{
	
	@Override
	public String getName() {
		return "Cross";
	}

	@Override
	public void draw(Effect eff, int color, int row, int colomn, Direction dir) {
		eff.setLedIfPossible(row, colomn, color);
		eff.setLedIfPossible(row+1, colomn, color);
		eff.setLedIfPossible(row-1, colomn, color);
		eff.setLedIfPossible(row, colomn+1, color);
		eff.setLedIfPossible(row, colomn-1, color);
	}
	
}
