package com.sinius15.lights.shapes;

import com.sinius15.lights.Effect;
import com.sinius15.lights.Shape;
import com.sinius15.lights.util.Direction;

public class DotShape extends Shape{

	@Override
	public String getName() {
		return "Dot";
	}

	@Override
	public void draw(Effect eff, int color, int row, int colomn, Direction dir) {
		eff.setLedOn(colomn, row, color);
	}
	
}
