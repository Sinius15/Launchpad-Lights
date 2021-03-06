package com.sinius15.lights.effects.window;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import com.sinius15.launchpad.OwnedLaunchpad;
import com.sinius15.lights.Effect;
import com.sinius15.lights.Save;
import com.sinius15.lights.options.BooleanOption;
import com.sinius15.lights.options.ScreenSelectOption;

public class WindowInitEffect extends Effect {

	@Save
	public BooleanOption initAll;

	@Save
	public ScreenSelectOption selectedScreen;

	public WindowInitEffect(OwnedLaunchpad pad, int row, int colomn) {
		super(pad, row, colomn);
		useAdvancedLight = null;

		selectedScreen = new ScreenSelectOption();
		initAll = new BooleanOption("Init all screens", false);
	}

	@Override
	public String getName() {
		return "Initalize Window";
	}

	@Override
	public String getDescription() {
		return "initializes the display.";
	}

	@Override
	public void buttonDown() {
		if(initAll.getValue()){
			for(GraphicsDevice device : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()){
				WindowManager.createScreen(device);
			}
		}else{
			WindowManager.createScreen(selectedScreen.getValue());
		}

	}

}
