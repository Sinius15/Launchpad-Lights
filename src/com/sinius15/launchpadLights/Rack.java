package com.sinius15.launchpadLights;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.sinius15.launchpad.events.ButtonListener;
import com.sinius15.launchpadLights.frame.ButtonEditFrame;
import com.sinius15.launchpadLights.frame.LightFrame;

public class Rack extends JPanel implements ButtonListener{
	
	private static final long serialVersionUID = 1L;
	
	public ButtonAction[][] buttons = new ButtonAction[9][9];
			
	public Rack() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		for(int x = 0; x < 9; x++){
			for(int y = 0; y < 9; y++){
				if(x == 8 && y == 0)
					continue;
				addButton(x, y);
			}
		}
		
	}
	
	public void setAnimation(int row, int col, Animation ani, ButtonActionType press, ButtonActionType release, ButtonActionType stop, int speed){
		buttons[row][col].setWaitTime(speed);
		buttons[row][col].setAnimation(ani);
		buttons[row][col].setPressType(press);
		buttons[row][col].setReleaseType(release);
		buttons[row][col].setAniStopType(stop);
		buttons[row][col].update();
	}
	
	private void addButton(final int col, final int row){
		ButtonAction newBtn = new ButtonAction(row+";"+col, null, 1000, ButtonActionType.nothing, ButtonActionType.nothing, ButtonActionType.nothing);
		newBtn.setForeground(Color.black);
		newBtn.setBackground(Color.white);
		final Rack rack = this;
		GridBagConstraints newGbc = new GridBagConstraints();
		newGbc.insets = new Insets(0, 0, 5, 5);
		newGbc.gridx = col;
		newGbc.gridy = row;
		newBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonEditFrame frame = new ButtonEditFrame(rack, row, col, buttons[row][col]);
				frame.setVisible(true);
				frame.setLocationRelativeTo(LightFrame.thiss);
				LightFrame.thiss.setEnabled(false);	
			}
		});
		add(newBtn, newGbc);
		buttons[row][col] = newBtn;
	}

	@Override
	public void onButtonDown(int row, int colomn) {
		buttons[row][colomn].onButtonDown();
	}

	@Override
	public void onButtonUp(int row, int colomn) {
		buttons[row][colomn].onButtonUp();
	}
}
