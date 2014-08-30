package com.sinius15.lights;

import javax.swing.JComponent;

public abstract class Option<T> {
	
	public abstract JComponent getComponent();
	
	public abstract String getTitle();
	
	public abstract T getValue();
	
	
}
