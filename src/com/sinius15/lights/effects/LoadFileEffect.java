package com.sinius15.lights.effects;

import java.io.File;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.FileLoader;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.FileOption;

public class LoadFileEffect extends Effect {

	@Save
	public FileOption fileChooser;

	public LoadFileEffect(OwnedLaunchpad pad, int row, int colomn) {
		super(pad, row, colomn);
		fileChooser = new FileOption();

		useAdvancedLight = null;
	}

	@Override
	public String getName() {
		return "Load File";
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public void buttonDown() {
		try{
			FileLoader.LoadFile(new File(fileChooser.getValue()));
		}catch(Exception e){
			System.out.println("could not load file. Sorry");
		}
	}
}
