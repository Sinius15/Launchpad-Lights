package com.sinius15.lights.effects.window;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.Rectangle;
import java.util.HashMap;

import javax.swing.JPanel;

public class WindowManager {

	private static HashMap<String, FullScreenFrame> visableFrames = new HashMap<>();


	public static void createScreen(GraphicsDevice device){
		if(visableFrames.containsKey(device.getIDstring()))
			return;//already inited

		Rectangle bounds = device.getDefaultConfiguration().getBounds();

		JPanel drawer = new JPanel();
		drawer.setBounds(0,  0, bounds.width, bounds.height);
		FullScreenFrame d = new FullScreenFrame(bounds);
		d.setContentPane(drawer);
		visableFrames.put(device.getIDstring(), d);

		d.getContentPane().getGraphics().setColor(Color.black);
		d.getContentPane().getGraphics().fillRect(0, 0, bounds.width, bounds.height);

	}

	public static void disableSCreen(GraphicsDevice device){
		if(!visableFrames.containsKey(device.getIDstring()))
			return;
		visableFrames.get(device.getIDstring()).dispose();
		visableFrames.remove(device.getIDstring());
	}

	public static Graphics2D getDrawPane(GraphicsDevice device){
		if(!visableFrames.containsKey(device.getIDstring()))
			return null;
		return  (Graphics2D) visableFrames.get(device.getIDstring()).getContentPane().getGraphics();
	}





}
