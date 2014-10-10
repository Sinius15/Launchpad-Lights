package com.sinius15.lights.shapes;

import com.sinius15.lights.Effect;
import com.sinius15.lights.Shape;
import com.sinius15.lights.util.Direction;

public class ArrowShape extends Shape {
	
	@Override
	public String getName() {
		return "Arrow";
	}
	
	@Override
	public void draw(Effect eff, int color, int row, int colomn, Direction dir) {
		eff.setLedOn(colomn, row, color);
		switch (dir) {
			case BOT_TOP:
				eff.setLedIfPossible(row-1, colomn, color);
				eff.setLedIfPossible(row, colomn+1, color);
				eff.setLedIfPossible(row, colomn-1, color);
				break;
			case LEF_RIG:
				eff.setLedIfPossible(row+1, colomn, color);
				eff.setLedIfPossible(row-1, colomn, color);
				eff.setLedIfPossible(row, colomn+1, color);
				break;
			case RIG_LEF:
				eff.setLedIfPossible(row+1, colomn, color);
				eff.setLedIfPossible(row-1, colomn, color);
				eff.setLedIfPossible(row, colomn-1, color);
				break;
			case TOP_BOT:
				eff.setLedIfPossible(row+1, colomn, color);
				eff.setLedIfPossible(row, colomn+1, color);
				eff.setLedIfPossible(row, colomn-1, color);
				break;
		}
	}
	
}
