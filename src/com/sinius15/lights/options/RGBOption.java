package com.sinius15.lights.options;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.sinius15.lights.Option;
import com.sinius15.lights.ui.JFrameLayered;

public class RGBOption extends Option<Color> {

	private String title;
	private JPanel panel;
	private JButton showSelectorBtn;

	private Color color = Color.black;

	public RGBOption(String title){
		this.title = title;

		showSelectorBtn = new JButton("Select Color...");
		showSelectorBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(JFrameLayered.getTopFrame(), "Select Color", getValue());
			}
		});

		panel = new JPanel();
		panel.add(showSelectorBtn);
	}

	@Override
	public String getSaveData() {
		return Integer.toString(color.getRGB());
	}

	@Override
	public void initFromSaveData(String saveData) {
		this.color = new Color(Integer.valueOf(saveData));
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
		return color;
	}

}
