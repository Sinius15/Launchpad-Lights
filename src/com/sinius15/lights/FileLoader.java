package com.sinius15.lights;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.sinius15.lights.util.YAMLFile;

/**
 * The class that provides functionality for loading data from a save file.
 *
 * @author Sinius15
 *
 */
public class FileLoader {

	public static void emptyProject(){
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				LaunchpadLightCreator.rack.effects[row][col] = null;
				if(LaunchpadLightCreator.rack.buttons[row][col] != null)
					LaunchpadLightCreator.rack.buttons[row][col].onButtonUp();
			}
		}
	}

	/**
	 * Loads a set from a file.
	 *
	 * @param file
	 *            The file to load.
	 */
	public static void LoadFile(File file) {
		YAMLFile data = new YAMLFile(true);
		data.Load(file);
		LaunchpadLightCreator.rack.effects = new Effect[9][9];

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				String effClassName = data.getString("button." + row + "."
						+ col);
				if (effClassName == null)
					continue;
				Class<? extends Effect> effClass = LaunchpadLightCreator
						.getEffectClass(effClassName);
				if (effClass == null) {
					System.err.println("could not find class " + effClassName);
					continue;
				}
				Effect eff = LaunchpadLightCreator.createIntance(effClass, row, col);
				LaunchpadLightCreator.rack.effects[row][col] = eff;

				for (Field field : effClass.getDeclaredFields()) {
					Annotation ann = field.getAnnotation(Save.class);
					if (ann == null)
						continue;

					try {
						Object o = field.get(eff);
						if (o instanceof Saveable) {
							Saveable toLoadIn = (Saveable) o;
							String optionSaveData = data.getString("button."
									+ row + "." + col + "." + field.getName());
							if (optionSaveData == null)
								continue;
							toLoadIn.initFromSaveData(optionSaveData);
						} else {
							throw new IllegalArgumentException("The variable "
									+ field.getName() + " Is not Saveable");
						}
					} catch (Exception e) {
						e.printStackTrace();
						System.out
								.println("Did not finish loading because of error.");
						return;
					}

				}

				LaunchpadLightCreator.rack.buttons[row][col].onButtonUp();
			}
		}
	}

	/**
	 * Saves a set to a file.
	 *
	 * @param file
	 *            The file to save the set to.
	 */
	public static void SaveFile(File file) {

		YAMLFile data = new YAMLFile(true);

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				Effect eff = LaunchpadLightCreator.rack.effects[row][col];
				if (eff == null)
					continue;
				data.addString("button." + row + "." + col, eff.getClass()
						.getName());
				Class<? extends Effect> effClass = eff.getClass();
				for (Field field : getOptionFields(effClass)) {
					try {
						Object o = field.get(eff);
						if(o == null)
							continue;
						if (o instanceof Saveable) {
							Saveable toSave = (Saveable) o;
							data.addString("button." + row + "." + col + "."
									+ field.getName(), toSave.getSaveData());
						} else {
							throw new IllegalArgumentException("The variable "
									+ field.getName() + " is not Saveable.");
						}
					} catch (Exception e) {
						e.printStackTrace();
						System.out
								.println("Did not finish saving because of error.");
						return;
					}

				}
			}
		}
		try {
			data.Save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Searches for fields that have a @save annotation present. Search in the
	 * class �nd in the superclass.
	 *
	 * @param effClass
	 *            the class to scan for valid fields.
	 * @return A list with all Fields that have annotation {@link Save}.
	 */
	public static Field[] getOptionFields(Class<? extends Effect> effClass) {
		ArrayList<Field> fields = new ArrayList<>();
		for (Field field : effClass.getDeclaredFields()) {
			Annotation ann = field.getAnnotation(Save.class);
			if (ann == null)
				continue;
			fields.add(field);
		}
		for (Field field : effClass.getSuperclass().getDeclaredFields()) {
			Annotation ann = field.getAnnotation(Save.class);
			if (ann == null)
				continue;
			fields.add(field);
		}
		return fields.toArray(new Field[fields.size()]);
	}

}
