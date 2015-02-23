package com.sinius15.lights.effects.window;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.Rectangle;
import java.util.HashMap;

/**
 * This class manages all the windows.
 * 
 * @author Sinius15
 * 
 */
public class WindowManager {

	/**
	 * A hashmap of all displayed windows. The key is a String retreived from
	 * {@link GraphicsDevice#getIDstring()}. This is the identification string
	 * for this window.
	 */
	private static HashMap<String, FullScreenFrame> visableFrames = new HashMap<>();

	/**
	 * crate a {@link FullScreenFrame} witch can be accessed from the
	 * {@link WindowManager#getDrawGraphics(GraphicsDevice)}
	 * 
	 * @param device
	 *            The device to fill with a drawer.
	 */
	public static void createScreen(GraphicsDevice device) {
		if (visableFrames.containsKey(device.getIDstring()))
			return;

		Rectangle bounds = device.getDefaultConfiguration().getBounds();

		FullScreenFrame d = new FullScreenFrame(bounds);
		visableFrames.put(device.getIDstring(), d);
	}

	/**
	 * Disables a window that was previously inited by
	 * {@link WindowManager#createScreen(GraphicsDevice)}
	 * 
	 * @param device
	 *            the device to disable the screen on.
	 */
	public static void disableSCreen(GraphicsDevice device) {
		if (!visableFrames.containsKey(device.getIDstring()))
			return;
		visableFrames.get(device.getIDstring()).dispose();
		visableFrames.remove(device.getIDstring());
	}

	/**
	 * Crates graphics witch can be used to draw on based on the givven device.
	 * 
	 * @param device
	 *            the device to get the graphics from
	 * @return the created graphics or null if the window is not yet created
	 */
	public static Graphics2D getDrawGraphics(GraphicsDevice device) {
		if (!visableFrames.containsKey(device.getIDstring()))
			return null;
		return (Graphics2D) visableFrames.get(device.getIDstring()).img
				.createGraphics();
	}

	/**
	 * Repaint all visable screens.
	 */
	public static void repaintScreens() {
		for (FullScreenFrame frame : visableFrames.values()) {
			frame.reDraw();
		}
	}

}
