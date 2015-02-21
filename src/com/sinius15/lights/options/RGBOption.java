package com.sinius15.lights.options;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.sinius15.lights.Option;

public class RGBOption extends Option<Color> {

	private String title;
	private JPanel panel;
	private JSpinner rSpin, gSpin, bSpin;

	public RGBOption(String title){
		this.title = title;

		rSpin = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
		gSpin = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
		bSpin = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));

		panel = new JPanel();

		panel.add(new JLabel("R"));
		panel.add(rSpin);
		panel.add(new JLabel("G"));
		panel.add(gSpin);
		panel.add(new JLabel("B"));
		panel.add(bSpin);
	}

	@Override
	public String getSaveData() {
		return Integer.toString(new Color((int)rSpin.getValue(), (int)gSpin.getValue(),(int)bSpin.getValue()).getRGB());
	}

	@Override
	public void initFromSaveData(String saveData) {
		Color color = new Color(Integer.valueOf(saveData));
		rSpin.setValue(color.getRed());
		gSpin.setValue(color.getGreen());
		bSpin.setValue(color.getBlue());
	}

	@Override
	public JComponent getComponent() {
		return panel;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Color getValue() {
		return new Color((int)rSpin.getValue(), (int)gSpin.getValue(),(int)bSpin.getValue());
	}

}
