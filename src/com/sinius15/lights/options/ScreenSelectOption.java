package com.sinius15.lights.options;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.sinius15.lights.Option;

public class ScreenSelectOption extends Option<GraphicsDevice> {

	private JComboBox<GraphicsDevice> comp;

	public ScreenSelectOption(){
		this.comp = new JComboBox<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices());
		this.comp.setSelectedItem((GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()));
	}

	@Override
	public JComponent getComponent() {
		return comp;
	}

	@Override
	public String getTitle() {
		return "Screen";
	}

	@Override
	public GraphicsDevice getValue() {
		return (GraphicsDevice) comp.getSelectedItem();
	}

	@Override
	public String getSaveData() {
		return ((GraphicsDevice) comp.getSelectedItem()).getIDstring();
	}

	@Override
	public void initFromSaveData(String saveData) {
		for(GraphicsDevice device : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()){
			if(device.getIDstring().equals(saveData)){
				comp.setSelectedItem(device);
				return;
			}
		}
	}

}
