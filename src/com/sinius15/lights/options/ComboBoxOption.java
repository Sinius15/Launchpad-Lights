package com.sinius15.lights.options;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.sinius15.lights.Option;

public class ComboBoxOption extends Option<String>{

	private String title;
	private JComboBox<String> comp;
	
	public ComboBoxOption(String title, String[] values, String defaultValue){
		this.title = title;
		this.comp = new JComboBox<>(values);
		this.comp.setSelectedItem(defaultValue);
	}
	
	@Override
	public JComponent getComponent() {
		return comp;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getValue() {
		return (String) comp.getSelectedItem();
	}
	
}
