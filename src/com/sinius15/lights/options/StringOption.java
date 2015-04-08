package com.sinius15.lights.options;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.sinius15.lights.Option;

public class StringOption extends Option<String>{

	private String title;
	private JTextField field;

	public StringOption(String title){

		this.title = title;
		field = new JTextField();
		field.setPreferredSize(new Dimension(100, 25));
	}

	@Override
	public String getSaveData() {
		return field.getText();
	}

	@Override
	public void initFromSaveData(String saveData) {
		this.field.setText(saveData);
	}

	@Override
	public JComponent getComponent() {
		return field;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getValue() {
		return field.getText();
	}

}
