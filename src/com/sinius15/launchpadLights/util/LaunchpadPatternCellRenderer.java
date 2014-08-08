package com.sinius15.launchpadLights.util;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import com.sinius15.launchpad.pattern.LaunchpadPattern;

public class LaunchpadPatternCellRenderer extends JLabel implements ListCellRenderer<LaunchpadPattern>{

	private static final long serialVersionUID = -1986233608334875698L;
	
	@SuppressWarnings("rawtypes")
	@Override
	public Component getListCellRendererComponent(JList list, LaunchpadPattern value, int index, boolean isSelected, boolean cellHasFocus) {
		setOpaque(true);
		setText(value.getName());
		if (isSelected) {
	    	setBackground(UIManager.getColor("List.selectionBackground"));
	    	setForeground(UIManager.getColor("List.selectionForeground"));
	    } else {
	    	setBackground(UIManager.getColor("List.background"));
	    	setForeground(UIManager.getColor("List.foreground"));
	    }
		return this;
	}
	
}
