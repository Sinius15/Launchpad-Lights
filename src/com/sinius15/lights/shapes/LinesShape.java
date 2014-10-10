package com.sinius15.lights.shapes;

import com.sinius15.lights.Effect;
import com.sinius15.lights.Shape;
import com.sinius15.lights.util.Direction;

public class LinesShape extends Shape{

	@Override
	public String getName() {
		return "Lines";
	}

	@Override
	public void draw(Effect eff, int color, int row, int colomn, Direction dir) {
		for(int i = 0; i < 9; i++){
			eff.setLedIfPossible(row, i, color);
			eff.setLedIfPossible(i, colomn, color);
		}
	}
	
}
