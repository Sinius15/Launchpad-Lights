package com.sinius15.lights.options;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

import com.sinius15.lights.Option;

public class BooleanOption extends Option<Boolean> {

	JCheckBox box = new JCheckBox();
	String title;
	
	public BooleanOption(String title, boolean defaultValue){
		this.title = title;
		box.setSelected(defaultValue);
	}
	
	@Override
	public JComponent getComponent() {
		return box;
	}

	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public Boolean getValue() {
		return box.isSelected();
	}

	@Override
	public String getSaveData() {
		return Boolean.toString(box.isSelected());
	}

	@Override
	public void initFromSaveData(String saveData) {
		box.setSelected(Boolean.valueOf(saveData));
	}

}
