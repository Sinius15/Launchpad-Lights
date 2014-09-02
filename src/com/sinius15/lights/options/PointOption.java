package com.sinius15.lights.options;

import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.sinius15.lights.Option;

public class PointOption extends Option<Point>{

	private static final long serialVersionUID = -317307707886644817L;
	
	private JPanel panel;
	private JSpinner colSpinner, rowSpinner;
	
	private String title;
	
	public PointOption(String title){
		this.title = title;
		
		colSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 8, 1));
		rowSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 8, 1));
		
		panel = new JPanel();
		
		panel.add(new JLabel("Row"));
		panel.add(rowSpinner);
		panel.add(new JLabel("Colomn"));
		panel.add(colSpinner);
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
	public Point getValue() {
		return new Point((int)rowSpinner.getValue(), (int)colSpinner.getValue());
	}

	@Override
	public String getSaveData() {
		return rowSpinner.getValue() + ":" + colSpinner.getValue();
	}

	@Override
	public void initFromSaveData(String saveData) {
		String[] split = saveData.split(":");
		rowSpinner.setValue(Integer.valueOf(split[0]));
		colSpinner.setValue(Integer.valueOf(split[1]));
	}
}
