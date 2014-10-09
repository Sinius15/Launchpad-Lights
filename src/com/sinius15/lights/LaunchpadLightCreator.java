package com.sinius15.lights;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.JOptionPane;

import org.jsresources.MidiCommon;

import com.sinius15.launchpad.BufferedLaunchpad;
import com.sinius15.launchpad.LaunchpadException;
import com.sinius15.lights.effects.BlockEffect;
import com.sinius15.lights.effects.ExplodingCross;
import com.sinius15.lights.effects.LoadFileEffect;
import com.sinius15.lights.effects.NoneEffect;
import com.sinius15.lights.effects.RandomBlinkEffect;
import com.sinius15.lights.effects.ShotEffect;
import com.sinius15.lights.effects.ShapeEffect;
import com.sinius15.lights.effects.WaveEffect;
import com.sinius15.lights.ui.LightFrame;

public class LaunchpadLightCreator {
	
	public static BufferedLaunchpad pad;
	public static ArrayList<Class< ? extends Effect>> effects = new ArrayList<>();
	
	public static Rack rack;
	
	public static void main(String[] args) {
		
		Shape.init();
		
		effects.add(NoneEffect.class);
		effects.add(WaveEffect.class);
		effects.add(ShotEffect.class);
		effects.add(ShapeEffect.class);
		effects.add(BlockEffect.class);
		effects.add(ExplodingCross.class);
		effects.add(LoadFileEffect.class);
		effects.add(RandomBlinkEffect.class);
		
		String deviceName = (String) JOptionPane.showInputDialog(null, "Chose your launchpad!",
		        "Launchpad Selector", JOptionPane.QUESTION_MESSAGE, null, MidiCommon.listDevices(true, false), "Launchpad S");
		
		
		try {
			pad = new BufferedLaunchpad(deviceName);
			pad.open();
			
			rack = new Rack();
			
			
			LightFrame frame = new LightFrame(rack);
			frame.setVisible(true);
			
			pad.addButtonListener(rack);
			
		} catch (LaunchpadException | MidiUnavailableException e) {
			e.printStackTrace();
			System.exit(0);
		}
		FileLoader.LoadFile(new File("testing.yml"));
	}
	
	public static Effect createIntance(Class< ? extends Effect> effClass, int row, int col){
		try {
			Class<?>[] argsClassArr = new Class[]{int.class, int.class};
			Constructor<? extends Effect> constructor = effClass.getConstructor(argsClassArr);
			return constructor.newInstance(row, col);
			
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
	}
	
	public static Class<? extends Effect> getEffectClass(String className){
		for(Class<? extends Effect> effClass : effects){
			if(effClass.getName().equals(className)){
				return effClass;
			}
		}
		return null;
	}
	
}
