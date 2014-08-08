package com.sinius15.launchpadLights;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collections;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.sinius15.launchpad.pattern.LaunchpadPattern;
import com.sinius15.launchpadLights.frame.LightFrame;

public class IOer implements Serializable {
	
	private static final long serialVersionUID = -6199404475421678896L;
	
	public static void saveAllToFile(File f) {
		try {
			copyEmptyDB(f);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		try {
			Database base = DatabaseBuilder.open(f);
			
			for (Column s : base.getTable("Animations").getColumns()) {
				System.out.println(s.toString());
			}
			
			int patternIdCounter = 0;
			int animationsIdCounter = 0;
			for (Animation ani : Data.animations) {
				String patternArray = "";
				for (LaunchpadPattern pat : ani.animations) {
					patternArray += patternIdCounter + ";";
					
					base.getTable("Pattern").addRow(pat.name, patternIdCounter, encodePattern(pat.data));
					
					patternIdCounter++;
				}
				patternArray = (patternArray.length()>0 ? patternArray.substring(0, patternArray.length() - 1) : patternArray);
				base.getTable("Animations").addRow(animationsIdCounter, ani.name, patternArray);
				animationsIdCounter++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
	}
	
	public static void loadAllFromFile(File f) {
		try {
			Database base = DatabaseBuilder.open(f);
			for (Row aniRow : base.getTable("Animations")) {
				String patternArray = (String) aniRow.get("patternArray");
				String[] patternIds = patternArray.split(";");
				
				Animation ani = new Animation();
				ani.name = (String) aniRow.get("naam");
				ani.animations = new LaunchpadPattern[patternIds.length];
				int i = 0;
				for (String id : patternIds) {
					if(id.equals(""))
						continue;
					Row patternRow = CursorBuilder.findRow(base.getTable("Pattern"), Collections.singletonMap("Id", Integer.parseInt(id)));
					ani.animations[i] = new LaunchpadPattern(decodePattern((String)patternRow.get("data")), (String) patternRow.get("naam"));
					i++;
				}
				Data.animations.add(ani);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LightFrame.thiss.updateAnimatinoList();
		LightFrame.thiss.updateRacks();
		
	}
	
	private static int[][] decodePattern(String in){
		int[][] out = new int[9][9];
		String[] rows = in.split("\\{");
		int r = -1; //somhow starting at -1 fixes the bug :0
		for(String row : rows){
			String[] nrs = row.split(",");
			int c = 0;
			for(String s : nrs){
				if(s.equals(""))
					continue;
				out[r][c] = Integer.parseInt(s);
				c++;
			}
			r++;
		}
		
		return out;
	}
	private static String encodePattern(int[][] in){
		String out = "";
		for (int[] e : in) {
			out += "{";
			for (int a : e) {
				out += a + ",";
			}
			out = out.substring(0, out.length() - 1);
		}
		System.out.println(out);
		return out;
	}
	
	private static void copyEmptyDB(File f) throws Exception {
		if(f.exists())
			f.delete();
		InputStream stream = LightFrame.class.getResourceAsStream("/emptyDB.accdb");
		if (stream == null) {
			stream = new FileInputStream("/emptyDB.accdb");
		}
		OutputStream resStreamOut = null;
		int readBytes;
		byte[] buffer = new byte[4096];
		
		resStreamOut = new FileOutputStream(f);
		while ((readBytes = stream.read(buffer)) > 0) {
			resStreamOut.write(buffer, 0, readBytes);
		}
		
		stream.close();
		resStreamOut.close();
		
	}
}
