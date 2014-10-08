package com.sinius15.lights.options;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.sinius15.lights.Option;

public class SpeedOption extends Option<Integer>{

	private JSpinner spinner;
	
	public SpeedOption(int defaultValue){
		this.spinner = new JSpinner(new SpinnerNumberModel(defaultValue, 0, Integer.MAX_VALUE, 1));
	}
	
	@Override
	public JComponent getComponent() {
		return this.spinner;
	}

	@Override
	public String getTitle() {
		return "Speed";
	}

	@Override
	public Integer getValue() {
		return (int) spinner.getValue();
	}

	@Override
	public String getSaveData() {
		return Integer.toString((int)spinner.getValue());
	}

	@Override
	public void initFromSaveData(String saveData) {
		spinner.setValue(Integer.valueOf(saveData));
	}
	
}
