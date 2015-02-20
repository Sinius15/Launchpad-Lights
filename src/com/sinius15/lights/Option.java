package com.sinius15.lights;

import javax.swing.JComponent;

/**
 * This is the abstract class represents
 * @author Sinius15
 * @param <T> is the Type that is going to be saved.
 */
public abstract class Option<T> implements Saveable{

	public abstract JComponent getComponent();
	
	public abstract String getTitle();
	
	public abstract T getValue();
	
}
