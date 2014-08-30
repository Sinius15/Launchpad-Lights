package com.sinius15.lights.options;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;

import com.sinius15.launchpad.Launchpad;
import com.sinius15.launchpad.events.ButtonListener;
import com.sinius15.launchpad.pattern.LaunchpadPattern;
import com.sinius15.lights.Option;

public class ColorOption extends Option<Integer> {

	private JPanel chooser;
	
	private ButtonGroup butGroup = new ButtonGroup();
	private int selectedColor = Launchpad.COLOR_GREEN_FULL;
	
	public ColorOption(){
		chooser = new JPanel();
		chooser.setLayout(null);
		chooser.setBounds(0, 0, 200, 250);
		chooser.setSize(200, 250);
		chooser.setPreferredSize(new Dimension(200, 250));
		
		JToggleButton b = addButton(Launchpad.COLOR_TRANSPARANT, Color.gray);
		b.setBounds(0, 200, 200, 50);
		b.setText("Transparent");
		
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
	public boolean validate(Integer value) {
		return value > 0;
	}

	@Override
	public Integer getValue() {
		return selectedColor;
	}

	@Override
	public Integer getDefaultValue() {
		return Launchpad.COLOR_GREEN_FULL;
	}
		
	private JToggleButton addButton(int lpColor, Color rgbColor){
		JToggleButton b = new JToggleButton("");
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
	
}
