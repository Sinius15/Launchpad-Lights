package com.sinius15.launchpadLights;

import java.io.Serializable;
import java.util.Arrays;

import com.sinius15.launchpad.pattern.LaunchpadPattern;

public class Animation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public String name;
	public LaunchpadPattern[] animations;
	
	public Animation(){}
	
	public Animation(String name, LaunchpadPattern[] animations) {
		this.name = name;
		this.animations = animations;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setAnimations(LaunchpadPattern[] animations) {
		this.animations = animations;
	}
	public LaunchpadPattern[] getAnimations() {
		return animations;
	}
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(animations);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
}
