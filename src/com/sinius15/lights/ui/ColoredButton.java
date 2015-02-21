package com.sinius15.lights.ui;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JButton;

import com.sinius15.lights.LaunchpadLightCreator;

/**
 * This class represents a button with a color. The color depends on the state
 * of the button on the launchpad. In the constructor you give the object a row
 * and a column. When {@link ColoredButton#onButtonDown()} or
 * {@link ColoredButton#onButtonUp()} is called, the color of the button will
 * react accordingly. This class depends on the
 * {@link LaunchpadLightCreator#rack} array. If this array is not yet
 * initialized, this class may cause some trouble.
 *
 * @author Sinius15
 */
public class ColoredButton extends JButton {

	private static final long serialVersionUID = -1083612329746590565L;

	/**
	 * The forground color of the button when the button has no effect and is
	 * not pressed.
	 */
	public static final Color FORGROUND_DEFAULT = Color.BLACK;

	/**
	 * The forground color of the button when the button has no effect and is
	 * pressed.
	 */
	public static final Color FORGROUND_DEFAULT_BUTTON_DOWN = Color.WHITE;

	/**
	 * The forground color of the button when the button has an effect and is
	 * not pressed.
	 */
	public static final Color FORGROUND_SELECTED = Color.BLACK;

	/**
	 * The forground color of the button when the button has an effect and is
	 * pressed.
	 */
	public static final Color FORGROUND_SELECTED_BUTTON_DOWN = Color.WHITE;

	/**
	 * The background color of the button when the button has no effect and is
	 * not pressed.
	 */
	public static final Color BACKGROUND_DEFAULT = Color.WHITE;
	/**
	 * The background color of the button when the button has no effect and is
	 * pressed.
	 */
	public static final Color BACKGROUND_DEFAULT_BUTTON_DOWN = Color.BLACK;
	/**
	 * The background color of the button when the button has an effect and is
	 * not pressed.
	 */
	public static final Color BACKGROUND_SELECTED = Color.RED;
	/**
	 * The background color of the button when the button has an effect and is
	 * pressed.
	 */
	public static final Color BACKGROUND_SELECTED_BUTTON_DOWN = Color.BLUE;

	/**
	 * The position of the phisical butotn on the launchpad that this virtual
	 * button represents. The x is the row, the y is the column.
	 */
	private Point position;

	/**
	 * Initializes a new instance of the ColoredButton class. See
	 * {@link ColoredButton} for information about this class.
	 *
	 * @param row
	 *            The row of the button on the phisical launchpad that this
	 *            virtual button represents.
	 * @param col
	 *            The column of the button on the phisical launchpad that this
	 *            virtual button represents.
	 */
	public ColoredButton(int row, int col) {
		super(row + ";" + col);
		position = new Point(row, col);
	}

	/**
	 * This function should be called when the phisical button on the launchpad
	 * is pressed. This function will update the button on screen in the
	 * according color.
	 */
	public void onButtonDown() {
		if (hasEffect()) {
			setForeground(FORGROUND_SELECTED_BUTTON_DOWN);
			setBackground(BACKGROUND_SELECTED_BUTTON_DOWN);
		} else {
			setForeground(FORGROUND_DEFAULT_BUTTON_DOWN);
			setBackground(BACKGROUND_DEFAULT_BUTTON_DOWN);
		}
		revalidate();
	}

	/**
	 * This function should be called when the phisical button on the launchpad
	 * is released. This function will update the button on screen in the
	 * according color.
	 */
	public void onButtonUp() {
		if (hasEffect()) {
			setForeground(FORGROUND_SELECTED);
			setBackground(BACKGROUND_SELECTED);
		} else {
			setForeground(FORGROUND_DEFAULT);
			setBackground(BACKGROUND_DEFAULT);
		}
		revalidate();
	}

	/**
	 * Checks if the button has an effect.
	 * @return true when a effect is present, false if the effect is epty.
	 */
	private boolean hasEffect() {
		return LaunchpadLightCreator.rack.effects[position.x][position.y] != null;
	}

}
