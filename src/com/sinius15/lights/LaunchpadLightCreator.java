package com.sinius15.lights;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import javax.sound.midi.MidiUnavailableException;

import com.sinius15.launchpad.BufferedLaunchpad;
import com.sinius15.launchpad.Launchpad;
import com.sinius15.launchpad.LaunchpadException;
import com.sinius15.lights.effects.StarEffect;
import com.sinius15.lights.effects.WaveEffect;
import com.sinius15.lights.ui.LightFrame;

public class LaunchpadLightCreator {
	
	public static BufferedLaunchpad pad;
	public static ArrayList<Class< ? extends Effect>> effects = new ArrayList<>();
	
	public static void main(String[] args) {
		
		effects.add(WaveEffect.class);
		effects.add(StarEffect.class);
		
		try {
			Class<?>[] argsClassArr = new Class[]{int.class, int.class, String.class};
			Constructor<? extends Effect> constructor = effects.get(0).getConstructor(argsClassArr);
			constructor.newInstance(12, 12, null);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			pad = new BufferedLaunchpad("Launchpad S");
			pad.open();
			LightFrame frame = new LightFrame();
			frame.setVisible(true);
			
			pad.addButtonListener(frame.launchRack);
			
		} catch (LaunchpadException | MidiUnavailableException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}
	
}
