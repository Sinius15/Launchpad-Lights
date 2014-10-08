package com.sinius15.lights.shapes;

import static com.sinius15.lights.util.Util.setLedIfPossible;

import com.sinius15.launchpad.Launchpad;
import com.sinius15.lights.Shape;
import com.sinius15.lights.util.Direction;

public class LinesShape extends Shape{

	@Override
	public String getName() {
		return "Lines";
	}

	@Override
	public void draw(Launchpad pad, int color, int row, int colomn, Direction dir) {
		for(int i = 0; i < 9; i++){
			setLedIfPossible(pad, row, i, color);
			setLedIfPossible(pad, i, colomn, color);
		}
	}
	
}
