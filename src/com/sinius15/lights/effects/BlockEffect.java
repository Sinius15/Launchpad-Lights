package com.sinius15.lights.effects;

import java.awt.Point;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.BooleanOption;
import com.sinius15.lights.options.ColorOption;
import com.sinius15.lights.options.KeepAliveOption;
import com.sinius15.lights.options.PointOption;

/**
 * The effect that shows a block on the launchpad.
 * The x, y, width, height and color are configurable.
 * @author Sinius15
 */
public class BlockEffect extends Effect {

	/**
	 * Where are the corners located?
	 */
	@Save
	public PointOption leftTopCorner, rightBottomCorner;

	/**
	 * What color does the block have?
	 */
	@Save
	public ColorOption colorSelector;

	/**
	 * Wether or not the block should be shown when the button is released.
	 */
	@Save
	public KeepAliveOption aliveOption;

	/**
	 * Wether or not to only show the outline of the block.
	 */
	@Save
	public BooleanOption showOutline;

	/**
	 * Is the button at this moment down or up?
	 */
	private boolean isButtonDown = false;

	/**
	 * When the button is released, should the colors be removed?
	 */
	private boolean removeLampsOnButtonUp = false;

	/**
	 * See {@link Effect#Effect(OwnedLaunchpad, int, int)}
	 */
	public BlockEffect(OwnedLaunchpad pad, int row, int colomn) {
		super(pad, row, colomn);
		leftTopCorner = new PointOption("Left top corner of the block:", PointOption.TYPE_LAUNCHPAD);
		rightBottomCorner = new PointOption("Right bottom corner of the block:", PointOption.TYPE_LAUNCHPAD);
		colorSelector = new ColorOption();
		aliveOption = new KeepAliveOption();
		showOutline = new BooleanOption("Show only outline?", false);
	}

	/**
	 * See {@link Effect#getName()}
	 */
	@Override
	public String getName() {
		return "Block";
	}

	/**
	 * See {@link Effect#getDescription()}
	 */
	@Override
	public String getDescription() {
		return "The effect that shows a block on the launchpad. The x, y, width, height and color are configurable.";
	}

	/**
	 * See {@link Effect#buttonDown()}
	 */
	@Override
	public void buttonDown() {
		isButtonDown = true;
		showLights();
		if (aliveOption.getValue() != -1) {
			removeLampsOnButtonUp = false;
			try {
				Thread.sleep(aliveOption.getValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (isButtonDown)
				removeLampsOnButtonUp = true;
			else
				removeLight();
		} else {
			removeLampsOnButtonUp = true;
		}

	}

	/**
	 * See {@link Effect#buttonUp()}
	 */
	@Override
	public void buttonUp() {
		isButtonDown = false;
		if (removeLampsOnButtonUp) {
			removeLight();
		}

	}

	/**
	 * Show the lights on the launchpad.
	 */
	private void showLights() {
		Point p1 = leftTopCorner.getValue();
		Point p2 = rightBottomCorner.getValue();
		if (showOutline.getValue()) {
			for (int row = p1.x; row <= p2.x; row++) {
				setLedOn(p1.y, row, colorSelector.getValue());
				setLedOn(p2.y, row, colorSelector.getValue());
			}
			for (int col = p1.y; col <= p2.y; col++) {
				setLedOn(col, p1.x, colorSelector.getValue());
				setLedOn(col, p2.x, colorSelector.getValue());
			}
		} else {
			for (int row = p1.x; row <= p2.x; row++) {
				for (int col = p1.y; col <= p2.y; col++) {
					setLedOn(col, row, colorSelector.getValue());
				}
			}
		}
	}

	/**
	 * Remove the lights on the launchpad.
	 */
	private void removeLight() {
		Point p1 = leftTopCorner.getValue();
		Point p2 = rightBottomCorner.getValue();

		if (showOutline.getValue()) {
			for (int row = p1.x; row <= p2.x; row++) {
				setLedOff(p1.y, row);
				setLedOff(p2.y, row);
			}
			for (int col = p1.y; col <= p2.y; col++) {
				setLedOff(col, p1.x);
				setLedOff(col, p2.x);
			}
		} else {
			for (int row = p1.x; row <= p2.x; row++) {
				for (int col = p1.y; col <= p2.y; col++) {
					setLedOff(col, row);
				}
			}
		}
	}
}
