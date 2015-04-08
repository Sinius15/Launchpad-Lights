package com.sinius15.lights.options;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

import say.swing.JFontChooser;

import com.sinius15.lights.Option;
import com.sinius15.lights.ui.JFrameLayered;

public class FontOption extends Option<Font>{

	private String title;
	private JButton btn;
	private JFontChooser chooser;

	public FontOption(String title){
		this.title = title;

		chooser = new JFontChooser();

		btn = new JButton("Select Font...");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.showDialog(JFrameLayered.getTopFrame());
			}
		});

	}

	@Override
	public String getSaveData() {


		return chooser.getSelectedFont().getFontName() + "-" + getStyleString(chooser.getSelectedFont().getStyle()) + "-" + chooser.getSelectedFont().getSize();
	}

	private static String getStyleString(int style){
		switch(style){
			case Font.PLAIN: return "PLAIN";
			case Font.BOLD: return "BOLD";
			case Font.ITALIC: return "ITALIC";
			case Font.BOLD+Font.ITALIC: return "BOLDITALIC";
			default: return "";
		}
	}

	@Override
	public void initFromSaveData(String saveData) {
		chooser.setSelectedFont(Font.decode(saveData));
	}

	@Override
	public JComponent getComponent() {
		return btn;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Font getValue() {
		return chooser.getSelectedFont();
	}

}
