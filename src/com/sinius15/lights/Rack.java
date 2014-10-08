package com.sinius15.lights;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import com.sinius15.launchpad.events.ButtonListener;
import com.sinius15.lights.ui.ButtonEditFrame;
import com.sinius15.lights.ui.ColoredButton;
import com.sinius15.lights.ui.EffectSettingsFrame;

public class Rack extends JPanel implements ButtonListener{
	
	private static final long serialVersionUID = 1L;
	
	public ColoredButton[][] buttons = new ColoredButton[9][9];
	
	public Effect[][] effects = new Effect[9][9]; //row, col
			
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
	
	private void addButton(final int col, final int row){
		ColoredButton newBtn = new ColoredButton(row,col);
		newBtn.setForeground(Color.black);
		newBtn.setBackground(Color.white);
		GridBagConstraints newGbc = new GridBagConstraints();
		newGbc.insets = new Insets(0, 0, 5, 5);
		newGbc.gridx = col;
		newGbc.gridy = row;
		
		newBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ButtonEditFrame(row, col).setVisible(true);
			}
		});

		final JPopupMenu p = new JPopupMenu();
		JMenuItem item = new JMenuItem("Effect Settings");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Effect eff = effects[row][col];
				Option<?>[] options = eff.getOptions();
				if(options != null)
					new EffectSettingsFrame(options).setVisible(true);
			}
		});
		p.add(item);
		
		newBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(effects[row][col] != null && e.isPopupTrigger()){
					p.show(e.getComponent(), e.getX(), e.getY());
				}
				super.mouseReleased(e);
			}
		});
		
		add(newBtn, newGbc);
		buttons[row][col] = newBtn;
	}

	@Override
	public void onButtonDown(final int row, final int colomn) {
		if(buttons[row][colomn] != null)
			buttons[row][colomn].onButtonDown();
		if(effects[row][colomn] != null)
			new Thread(new Runnable() {
				@Override
				public void run() {
					effects[row][colomn].buttonDown(LaunchpadLightCreator.pad);
				}
			}).start();
		
	}

	@Override
	public void onButtonUp(final int row, final int colomn) {
		if(buttons[row][colomn] != null)
			buttons[row][colomn].onButtonUp();
		if(effects[row][colomn] != null)
			new Thread(new Runnable() {
				@Override
				public void run() {
					effects[row][colomn].buttonUp(LaunchpadLightCreator.pad);
				}
			}).start();
		
	}
}
