package com.sinius15.lights.effects.window;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.BooleanOption;
import com.sinius15.lights.options.ScreenSelectOption;

public class WindowDisableEffect extends Effect {

	@Save
	public BooleanOption initAll;

	@Save
	public ScreenSelectOption selectedScreen;

	public WindowDisableEffect(OwnedLaunchpad pad, int row, int colomn) {
		super(pad, row, colomn);
		useAdvancedLight = null;

		selectedScreen = new ScreenSelectOption();
		initAll = new BooleanOption("Disable all screens", false);
	}

	@Override
	public String getName() {
		return "Disable Window";
	}

	@Override
	public String getDescription() {
		return "Disable the display(s).";
	}

	@Override
	public void buttonDown() {
		if(initAll.getValue()){
			for(GraphicsDevice device : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()){
				WindowManager.disableSCreen(device);
			}
		}else{
			WindowManager.disableSCreen(selectedScreen.getValue());
		}

	}

}
