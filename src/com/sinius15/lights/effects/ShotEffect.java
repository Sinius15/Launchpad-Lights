package com.sinius15.lights.effects;

import com.sinius15.launchpad.BufferedLaunchpad;
import com.sinius15.launchpad.Launchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Option;
import com.sinius15.lights.Save;
import com.sinius15.lights.Shape;
import com.sinius15.lights.options.ColorOption;
import com.sinius15.lights.options.ComboBoxOption;
import com.sinius15.lights.options.DirectionOption;
import com.sinius15.lights.options.SpeedOption;

public class ShotEffect extends Effect {
	
	private static final long serialVersionUID = 7283296373490151239L;
	
	@Save
	public ColorOption colorChooser;
	@Save
	public DirectionOption dirChooser;
	@Save
	public SpeedOption speedChooser;
	@Save
	public ComboBoxOption shapeChooser;
	
	public ShotEffect(int row, int colomn) {
		super(row, colomn);
		colorChooser = new ColorOption();
		dirChooser = new DirectionOption();
		speedChooser = new SpeedOption(50);
		
		shapeChooser = new ComboBoxOption("Shape", Shape.shapeStrings, Shape.shapes[0].getName());
	}

	@Override
	public void buttonDown(BufferedLaunchpad launchpad) {
		int color = colorChooser.getValue();
		int curRow = row;
		int curCol = colomn;
		do {
			setLedsOn(launchpad, curCol, curRow, color);
			try {
				Thread.sleep(speedChooser.getValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setLedsOn(launchpad, curCol, curRow, Launchpad.COLOR_OFF);
			switch (dirChooser.getValue()) {
				case BOT_TOP:
					curRow--;
					break;
				case LEF_RIG:
					curCol++;
					break;
				case RIG_LEF:
					curCol--;
					break;
				case TOP_BOT:
					curRow++;
					break;
				
			}
		} while (curCol >= 0 && curRow >= 0 && curCol <= 8 && curRow <= 8);
	}
	
	private void setLedsOn(Launchpad pad, int colomn, int row, int color) {
		Shape.stringToShape(
				shapeChooser.getValue())
		.draw(pad, color, row, colomn, 
				dirChooser.getValue());
	}
	
	@Override
	public String getName() {
		return "Shot Effect";
	}
	
	@Override
	public String getDescription() {
		return null;
	}
	
	@Override
	public Option<?>[] getOptions() {
		return new Option<?>[]{dirChooser, shapeChooser, speedChooser, colorChooser};
	}
	
}
