package com.sinius15.lights.util;

import com.sinius15.launchpad.BufferedLaunchpad;
import com.sinius15.launchpad.Launchpad;

public class Util {
	
	public static void colorRow(BufferedLaunchpad launchpad, int color, int row) {
		if(row < 0 || row > 8)
			return;
		for(int i = 0; i <= 8; i++){
			launchpad.setLedOn(i, row, color);
		}
	}
	
	public static void colorColomn(BufferedLaunchpad launchpad, int color, int colomn) {
		for(int i = 0; i <= 8; i++){
			launchpad.setLedOn(colomn, i, color);
		}
	}
	
	public static void setLedIfPossible(Launchpad pad, int row, int colomn, int color){
		if(row < 0 || row > 8 || colomn < 0 || colomn > 8)
			return;
		pad.setLedOn(colomn, row, color);
	}
	
}
