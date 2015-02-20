package com.sinius15.lights;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.JOptionPane;

import org.jsresources.MidiCommon;

import com.sinius15.launchpad.LaunchpadException;
import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.effects.BlockEffect;
import com.sinius15.lights.effects.ExplodingCross;
import com.sinius15.lights.effects.LoadFileEffect;
import com.sinius15.lights.effects.NoneEffect;
import com.sinius15.lights.effects.RandomBlinkEffect;
import com.sinius15.lights.effects.ShapeEffect;
import com.sinius15.lights.effects.ShotEffect;
import com.sinius15.lights.effects.WaveEffect;
import com.sinius15.lights.effects.window.WindowInitEffect;
import com.sinius15.lights.ui.LightFrame;

public class LaunchpadLightCreator {

	/**
	 * The launchpad! This represents the phisical novation launchpad. This
	 * object will send events when buttons are pressed, and we will respond
	 * with commands to turn on and off lights.
	 */
	public static OwnedLaunchpad pad;

	/**
	 * A list with all avalable effects. The classes are stored, so every button
	 * gets a new instance.
	 */
	public static ArrayList<Class<? extends Effect>> effects = new ArrayList<>();

	/**
	 * The Rack rack.
	 */
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
		effects.add(WindowInitEffect.class);

		String deviceName = (String) JOptionPane.showInputDialog(null,
				"Chose your launchpad!", "Launchpad Selector",
				JOptionPane.QUESTION_MESSAGE, null,
				MidiCommon.listDevices(true, false), "Launchpad S");
		if (deviceName == null || deviceName.equals(""))
			return;
		try {
			pad = new OwnedLaunchpad(deviceName);
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

	/**
	 * Creates a {@link Effect} instance of the given class.
	 *
	 * @param effClass
	 *            The class to construct the Effect with.
	 * @param row
	 *            The row on the launchpad this effect is on.
	 * @param col
	 *            The colomun on the launchpad this effect is on.
	 * @return A new {@link Effect}.
	 */
	public static Effect createIntance(Class<? extends Effect> effClass,
			int row, int col) {
		try {
			Class<?>[] argsClassArr = new Class[] { OwnedLaunchpad.class,
					int.class, int.class };
			Constructor<? extends Effect> constructor = effClass
					.getConstructor(argsClassArr);
			return constructor.newInstance(pad, row, col);
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
	}

	/**
	 * Searches for a class that extends {@link Effect}.
	 *
	 * @param className
	 *            The name of the class to search for.
	 * @return a class with the specified classname or null when no class was
	 *         found.
	 */
	public static Class<? extends Effect> getEffectClass(String className) {
		for (Class<? extends Effect> effClass : effects) {
			if (effClass.getName().equals(className)) {
				return effClass;
			}
		}
		return null;
	}

}
