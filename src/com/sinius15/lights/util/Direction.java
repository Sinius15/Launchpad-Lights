package com.sinius15.lights.util;

public enum Direction {
	TOP_BOT("Top to Bottom"), BOT_TOP("Bottom to Top"), LEF_RIG("Left to Right"), RIG_LEF("Right to Left");
	
	public String name;
	
	Direction(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
	public static Direction fromString(String inName){
		for(Direction d : Direction.values()){
			if(d.name.equals(inName))
				return d;
		}
		return null;
	}
	
	public static String[] Values(){
		return new String[]{TOP_BOT.name, BOT_TOP.name, LEF_RIG.name, RIG_LEF.name};
	}
}
