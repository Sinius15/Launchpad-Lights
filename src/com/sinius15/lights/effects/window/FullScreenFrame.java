package com.sinius15.lights.effects.window;

import java.awt.Rectangle;

import javax.swing.JFrame;

public class FullScreenFrame extends JFrame {

	private static final long serialVersionUID = -7516871245848941094L;

	public FullScreenFrame(Rectangle bounds) {
		super("Fullscreen");
		setUndecorated(true);
		setResizable(false);
		setUndecorated(true);
		setBounds(bounds);
		setAlwaysOnTop(true);
		setVisible(true);
	}
}
