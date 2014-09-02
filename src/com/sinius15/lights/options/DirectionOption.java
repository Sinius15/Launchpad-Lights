package com.sinius15.lights.options;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.sinius15.lights.Option;
import com.sinius15.lights.util.Direction;

public class DirectionOption extends Option<Direction> {

	private static final long serialVersionUID = 6611839912062300679L;
	
	private JComboBox<String> comp;
	
	public DirectionOption(){
		this.comp = new JComboBox<>(Direction.Values());
		this.comp.setSelectedItem(Direction.TOP_BOT.name);
	}
	
	@Override
	public JComponent getComponent() {
		return comp;
	}

	@Override
	public String getTitle() {
		return "Direction";
	}

	@Override
	public Direction getValue() {
		return Direction.fromString((String) comp.getSelectedItem());
	}
	
	@Override
	public String getSaveData() {
		return Integer.toString(comp.getSelectedIndex());
	}

	@Override
	public void initFromSaveData(String saveData) {
		comp.setSelectedIndex(Integer.valueOf(saveData));
	}
	
}
