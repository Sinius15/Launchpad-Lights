package com.sinius15.launchpadLights;

public enum ButtonActionType {
	
	play("Start Animation"),
	stop("Stop Animation"),
	cleanstop("Stop Animation and CleanUp"),
	reset("Reset the launchpad"),
	nothing("Do Nothing");
	
	private final String descr;
	
	ButtonActionType(String description){
		this.descr = description;
	}
	
	public String getDescription(){
		return descr;
	}
	
	public static ButtonActionType getByDescription(String description){
		for(ButtonActionType b : values()){
			if(b.descr.equals(description))
				return b;
		}
		return null;
	}
	
	
	
}
