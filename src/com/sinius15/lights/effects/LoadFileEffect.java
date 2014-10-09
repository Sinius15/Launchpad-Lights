package com.sinius15.lights.effects;

import java.io.File;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.FileLoader;
import com.sinius15.lights.Option;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.FileOption;

public class LoadFileEffect extends Effect {
	
	@Save
	public FileOption fileChooser;
	
	public LoadFileEffect(int row, int colomn) {
		super(row, colomn);
		fileChooser = new FileOption();
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
	public void buttonDown(OwnedLaunchpad launchpad) {
		FileLoader.LoadFile(new File(fileChooser.getValue()));
	}
	
	@Override
	public Option<?>[] getOptions() {
		return new Option<?>[] { fileChooser };
	}
}
