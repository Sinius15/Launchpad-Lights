package com.sinius15.lights.util;

import com.sinius15.launchpad.OwnedLaunchpad;

public class Util {
	
	public static void colorRow(OwnedLaunchpad launchpad, int color, int row, String owner) {
		if(row < 0 || row > 8)
			return;
		for(int i = 0; i <= 8; i++){
			launchpad.setLedOn(i, row, color, owner);
		}
	}
	
	public static void colorColomn(OwnedLaunchpad launchpad, int color, int colomn, String owner) {
		for(int i = 0; i <= 8; i++){
			launchpad.setLedOn(colomn, i, color, owner);
		}
	}
	
	public static void setLedIfPossible(OwnedLaunchpad pad, int row, int colomn, int color, String owner){
		if(row < 0 || row > 8 || colomn < 0 || colomn > 8)
			return;
		pad.setLedOn(colomn, row, color, owner);
	}
	
}
