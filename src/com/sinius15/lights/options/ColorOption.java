package com.sinius15.lights.options;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import com.sinius15.launchpad.Launchpad;
import com.sinius15.lights.Option;

public class ColorOption extends Option<Integer> {

	private JPanel chooser;
	
	private ButtonGroup butGroup = new ButtonGroup();
	private int selectedColor = Launchpad.COLOR_GREEN_FULL;
	
	private static final int RANDOMCOLOR = -123456;
	
	public ColorOption(){
		chooser = new JPanel();
		chooser.setLayout(null);
		chooser.setBounds(0, 0, 200, 250);
		chooser.setSize(200, 250);
		chooser.setPreferredSize(new Dimension(200, 250));
		
		JToggleButton b2 = addButton(RANDOMCOLOR, Color.GRAY);
		b2.setBounds(0, 200, 200, 50);
		b2.setText("Random Color");
		
		//add buttons
		for (int green = 0; green < 4; green++) {
			for (int red = 0; red < 4; red++) {
				int lpColor = Launchpad.calculateColour(green, red);
				addButton(lpColor, Launchpad.lpColorToRGB(green, red)).setBounds(green*50, red*50, 50, 50);
			}
		}
	}
	
	@Override
	public JComponent getComponent() {
		return this.chooser;
	}

	@Override
	public String getTitle() {
		return "Color";
	}

	@Override
	public Integer getValue() {
		if(selectedColor == RANDOMCOLOR)
			return randomColor();
		return selectedColor;
	}

	private static int[] randomColors = new int[]{Launchpad.COLOR_AMBER_FULL, Launchpad.COLOR_GREEN_FULL, Launchpad.COLOR_RED_FULL,
			Launchpad.COLOR_AMBER_LOW, Launchpad.COLOR_GREEN_LOW, Launchpad.COLOR_RED_LOW};
	private static Random random = new Random();
	public static int randomColor(){
		return randomColors[random.nextInt(randomColors.length)];
	}
		
	private JToggleButton addButton(int lpColor, Color rgbColor){
		JToggleButton b = new JToggleButton("***");
		butGroup.add(b);
		
		b.setBorder(BorderFactory.createEmptyBorder());
		
		b.setBackground(rgbColor);
		b.setForeground(Color.black);
		
		b.setContentAreaFilled(false);
		b.setOpaque(true);
		
		b.addActionListener(new ActionListener() {
			private int lpColor;
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedColor = this.lpColor;
			}
			public ActionListener setCol(int col){
				this.lpColor = col;
				return this;
			}
		}.setCol(lpColor));
		chooser.add(b);
		return b;
	}

	@Override
	public String getSaveData() {
		return Integer.toString(selectedColor);
	}

	@Override
	public void initFromSaveData(String saveData) {
		selectedColor = Integer.valueOf(saveData);
	}
	
}
