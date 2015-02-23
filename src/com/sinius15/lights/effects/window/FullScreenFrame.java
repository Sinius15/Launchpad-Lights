package com.sinius15.lights.effects.window;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A frame that covers the whole screen.
 * 
 * @author Sininius15
 * 
 */
public class FullScreenFrame extends JFrame {

	private static final long serialVersionUID = -7516871245848941094L;

	/**
	 * The panel where is drawn on
	 */
	private DrawPane drawer;

	/**
	 * The image that creates a buffer. Everything is painted on this image,
	 * this creates like a buffer.
	 */
	public final BufferedImage img;

	public FullScreenFrame(Rectangle bounds) {
		super("Fullscreen");
		img = new BufferedImage(bounds.width, bounds.height,
				BufferedImage.TYPE_INT_RGB);
		drawer = new DrawPane();
		drawer.setPreferredSize(bounds.getSize());
		drawer.setSize(bounds.getSize());
		setSize(bounds.getSize());
		getContentPane().add(drawer, BorderLayout.CENTER);

		setUndecorated(true);
		setResizable(false);
		setBounds(bounds);
		setAlwaysOnTop(true);
		setVisible(true);

		validate();
	}

	/**
	 * @return the panel to draw upon
	 */
	public DrawPane getDrawPane() {
		return drawer;
	}

	/**
	 * Repaints the whole screen.
	 */
	public void reDraw() {
		drawer.revalidate();
		drawer.repaint();
		revalidate();
		repaint();
	}

	/**
	 * A jpanel that draws the image when painted
	 * @author Sinius15
	 */
	public class DrawPane extends JPanel {

		private static final long serialVersionUID = 8096844582901660090L;

		@Override
		public void paintComponent(Graphics gg) {
			super.paintComponent(gg);
			gg.drawImage(img, 0, 0, null);
		}
	}
}
