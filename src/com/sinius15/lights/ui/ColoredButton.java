package com.sinius15.lights.ui;

import java.awt.Color;

import javax.swing.JButton;

import com.sinius15.lights.LaunchpadLightCreator;

public class ColoredButton extends JButton{
	
	private static final long serialVersionUID = -1083612329746590565L;
	
	public static final Color FORGROUND_DEFAULT = Color.BLACK;
	public static final Color FORGROUND_DEFAULT_BUTTON_DOWN = Color.WHITE;
	public static final Color FORGROUND_SELECTED = Color.BLACK;
	public static final Color FORGROUND_SELECTED_BUTTON_DOWN = Color.WHITE;
	
	public static final Color BACKGROUND_DEFAULT = Color.WHITE;
	public static final Color BACKGROUND_DEFAULT_BUTTON_DOWN = Color.BLACK;
	public static final Color BACKGROUND_SELECTED = Color.RED;
	public static final Color BACKGROUND_SELECTED_BUTTON_DOWN = Color.BLUE;
	
	private int row, col;
	
	public ColoredButton(int row, int col) {
		super(row+";"+col);
		this.row = row;
		this.col = col;
	}

	public void onButtonDown(){
		if(hasEffect()){
			setForeground(FORGROUND_SELECTED_BUTTON_DOWN);
			setBackground(BACKGROUND_SELECTED_BUTTON_DOWN);
		}else{
			setForeground(FORGROUND_DEFAULT_BUTTON_DOWN);
			setBackground(BACKGROUND_DEFAULT_BUTTON_DOWN);
		}
	}
	
	public void onButtonUp(){
		colorStandardColor();
	}
	
	private boolean hasEffect(){
		return LaunchpadLightCreator.rack.effects[row][col] != null;
	}

	public void colorStandardColor() {
		if(hasEffect()){
			setForeground(FORGROUND_SELECTED);
			setBackground(BACKGROUND_SELECTED);
		}else{
			setForeground(FORGROUND_DEFAULT);
			setBackground(BACKGROUND_DEFAULT);
		}
	}


}
