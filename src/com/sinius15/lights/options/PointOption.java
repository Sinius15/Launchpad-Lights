package com.sinius15.lights.options;

import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.sinius15.lights.Option;

public class PointOption extends Option<Point>{

	public static final int TYPE_LAUNCHPAD = 0;
	public static final int TYPE_SCREEN = 1;

	private JPanel panel;
	private JSpinner colSpinner, rowSpinner;

	private String title;

	public PointOption(String title, int type){
		this.title = title;

		String label1 = "Row";
		String label2 = "Colomn";

		if(type == TYPE_LAUNCHPAD){
			colSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 8, 1));
			rowSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 8, 1));
		}else if(type == TYPE_SCREEN){
			colSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1));
			rowSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 9999, 1));

			label1 = "X";
			label2 = "Y";
		}else{
			return;
		}

		panel = new JPanel();

		panel.add(new JLabel(label1));
		panel.add(rowSpinner);
		panel.add(new JLabel(label2));
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
