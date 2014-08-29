package com.sinius15.lights.ui;

import java.awt.Color;

import javax.swing.JButton;

public class ColoredButton extends JButton{
	
	private static final long serialVersionUID = -1083612329746590565L;
	
	public ColoredButton(String title) {
		super(title);
	}

	public void onButtonDown(){
		setForeground(Color.white);
		setBackground(Color.black);
	}
	
	public void onButtonUp(){
		setForeground(Color.black);
		setBackground(Color.white);
	}


}
