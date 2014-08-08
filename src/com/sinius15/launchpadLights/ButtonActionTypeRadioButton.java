package com.sinius15.launchpadLights;

import javax.swing.JRadioButton;

public class ButtonActionTypeRadioButton extends JRadioButton {

	private static final long serialVersionUID = -1304550667410758351L;
	
	private ButtonActionType type;
	
	public ButtonActionTypeRadioButton(ButtonActionType type) {
		super(type.getDescription());
		this.type = type;
	}

	public ButtonActionType getType() {
		return type;
	}

	public void setType(ButtonActionType type) {
		this.type = type;
	}
}
