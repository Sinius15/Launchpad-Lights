package com.sinius15.lights.options;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sinius15.lights.Option;

public class FileOption extends Option<String>{
	
	private JPanel panel;
	private JTextField textField;
	private JButton button;
	
	public FileOption(){
		panel = new JPanel();
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(100, 25));
		button = new JButton("...");
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				filechooser.setAcceptAllFileFilterUsed(true);
				filechooser.setDialogType(JFileChooser.OPEN_DIALOG);
				int returnErrorNR = filechooser.showSaveDialog(null);
				if(returnErrorNR != 0)
					return;
				textField.setText(filechooser.getSelectedFile().getAbsolutePath());
			}
		});
		
		panel.add(textField);
		panel.add(button);
	}
	
	@Override
	public JComponent getComponent() {
		return panel;
	}

	@Override
	public String getTitle() {
		return "File";
	}

	@Override
	public String getValue() {
		return textField.getText();
	}

	@Override
	public String getSaveData() {
		return textField.getText();
	}

	@Override
	public void initFromSaveData(String saveData) {
		textField.setText(saveData);
	}
	
}
