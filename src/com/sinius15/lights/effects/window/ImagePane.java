package com.sinius15.lights.effects.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePane extends JPanel {

	private static final long serialVersionUID = -9048329327500348936L;

	private final BufferedImage img;

	public ImagePane(int width, int height) {
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
		super.paint(g);
	}
}