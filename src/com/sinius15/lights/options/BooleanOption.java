package com.sinius15.lights.options;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

import com.sinius15.lights.Option;

public class BooleanOption extends Option<Boolean> {

	JCheckBox box = new JCheckBox();
	
	@Override
	public JComponent getComponent() {
		return box;
	}

	@Override
	public String getTitle() {
		return "boolean";
	}

	@Override
	public boolean validate(Boolean value) {
		return true;
	}

	@Override
	public Boolean getValue() {
		return box.isSelected();
	}

	@Override
	public Boolean getDefaultValue() {
		return false;
	}

}
