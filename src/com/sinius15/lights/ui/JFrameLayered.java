package com.sinius15.lights.ui;

import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * Wooot! Layered frames!<br>
 * @author Sinius15
 *
 */
public abstract class JFrameLayered extends JFrame{
	
	private static final long serialVersionUID = -4159135430535541691L;
	
	private static ArrayList<JFrameLayered> activeLayers = new ArrayList<>();
	
	public JFrameLayered(){
		for(JFrameLayered l : activeLayers){
			l.setEnabled(false);
		}
		
		activeLayers.add(this);
	}
	
	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		if(activeLayers.size() <= 1){
			setLocationRelativeTo(null);
		}else{
			setLocationRelativeTo(activeLayers.get(activeLayers.size()-2));
		}
	}
	
	@Override
	public void dispose() {
		super.dispose();
		activeLayers.remove(this);
		for(JFrameLayered l : activeLayers){
			l.toFront();
		}
		if(activeLayers.size() > 0)
			activeLayers.get(activeLayers.size()-1).setEnabled(true);
	}
	
}
