package com.sinius15.lights.effects.window;

import java.awt.GraphicsDevice;
import java.awt.Rectangle;
import java.util.HashMap;

public class WindowManager {

	private static HashMap<String, FullScreenFrame> visableFrames = new HashMap<>();


	public static void createScreen(GraphicsDevice device){
		if(visableFrames.containsKey(device.getIDstring()))
			return;//already inited

		Rectangle bounds = device.getDefaultConfiguration().getBounds();

		ImagePane drawer = new ImagePane(bounds.width, bounds.height);
		FullScreenFrame d = new FullScreenFrame(bounds);
		d.setContentPane(drawer);
		visableFrames.put(device.getIDstring(), d);
	}

	public static void disableSCreen(GraphicsDevice device){
		if(!visableFrames.containsKey(device.getIDstring()))
			return;
		visableFrames.get(device.getIDstring()).dispose();
		visableFrames.remove(device.getIDstring());
	}

	public static ImagePane getDrawPane(GraphicsDevice device){
		if(!visableFrames.containsKey(device.getIDstring()))
			return null;
		return (ImagePane) visableFrames.get(device.getIDstring()).getContentPane();
	}





}
