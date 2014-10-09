package com.sinius15.lights.options;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.sinius15.lights.Option;

public class IntOption extends Option<Integer> {

	private JSpinner spinner;
	private String title = "Speed";
	private int min = 0;
	private int max = Integer.MAX_VALUE;
	private int defaultValue = 0;

	public IntOption(int defaultValue) {
		this.spinner = new JSpinner(new SpinnerNumberModel(defaultValue, min, max, 1));
		this.defaultValue = defaultValue;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMax(int max) {
		this.max = max;
		updateSpinner();
	}

	public void setMin(int min) {
		this.min = min;
		updateSpinner();
	}

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}

	private void updateSpinner(){
		this.spinner.setModel(new SpinnerNumberModel(defaultValue, min, max, 1));
	}
	
	@Override
	public JComponent getComponent() {
		return this.spinner;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Integer getValue() {
		return (int) spinner.getValue();
	}

	@Override
	public String getSaveData() {
		return Integer.toString((int) spinner.getValue());
	}

	@Override
	public void initFromSaveData(String saveData) {
		spinner.setValue(Integer.valueOf(saveData));
	}

}
