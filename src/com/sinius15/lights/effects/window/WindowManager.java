package com.sinius15.lights.effects.window;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.Rectangle;
import java.util.HashMap;

public class WindowManager {

	private static HashMap<String, FullScreenFrame> visableFrames = new HashMap<>();

	public static void createScreen(GraphicsDevice device) {
		if (visableFrames.containsKey(device.getIDstring()))
			return;

		Rectangle bounds = device.getDefaultConfiguration().getBounds();

		FullScreenFrame d = new FullScreenFrame(bounds);
		visableFrames.put(device.getIDstring(), d);
	}

	public static void disableSCreen(GraphicsDevice device) {
		if (!visableFrames.containsKey(device.getIDstring()))
			return;
		visableFrames.get(device.getIDstring()).dispose();
		visableFrames.remove(device.getIDstring());
	}

	public static Graphics2D getDrawGraphics(GraphicsDevice device) {
		if (!visableFrames.containsKey(device.getIDstring()))
			return null;
		return (Graphics2D) visableFrames.get(device.getIDstring()).img.createGraphics();
	}

	public static void repaintScreens() {
		for (FullScreenFrame frame : visableFrames.values()) {
			frame.reDraw();
		}
	}

}
