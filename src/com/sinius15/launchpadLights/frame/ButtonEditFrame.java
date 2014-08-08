package com.sinius15.launchpadLights.frame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.sinius15.launchpadLights.Animation;
import com.sinius15.launchpadLights.ButtonAction;
import com.sinius15.launchpadLights.ButtonActionType;
import com.sinius15.launchpadLights.ButtonActionTypeRadioButton;
import com.sinius15.launchpadLights.Data;
import com.sinius15.launchpadLights.Rack;

public class ButtonEditFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private ButtonGroup buttonPressGroup = new ButtonGroup();
	private ButtonGroup buttonUpGroup = new ButtonGroup();
	private ButtonGroup buttonStopGroup = new ButtonGroup();
	
	private JTextField textField;
	private JLabel lblSelectAnimation;
	private JComboBox<Animation> aniSelector;
	
	private ButtonActionTypeRadioButton[] buttonActionsUp = new ButtonActionTypeRadioButton[ButtonActionType.values().length];
	private ButtonActionTypeRadioButton[] buttonActionsDown = new ButtonActionTypeRadioButton[ButtonActionType.values().length];
	private ButtonActionTypeRadioButton[] buttonActionsStop = new ButtonActionTypeRadioButton[ButtonActionType.values().length];
	
	private int row, col;
	private Rack rack;
	
	private ActionListener saveAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int newSpeed;
			try{
				newSpeed = Integer.parseInt(textField.getText());
			}catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Speed is not a number.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			rack.setAnimation(row, col, (Animation) aniSelector.getSelectedItem(),
					getSelectedActionButtonTypeDown(), getSelectedActionButtonTypeUp(),
					getSelectedActionButtonTypeStop(), newSpeed);
			dispose();
			LightFrame.thiss.setEnabled(true);
			LightFrame.thiss.requestFocus();
		}
	};
	
	public ButtonEditFrame(Rack rack, int row, int col, ButtonAction action) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				LightFrame.thiss.setEnabled(true);
				LightFrame.thiss.requestFocus();
			}
		});
		this.row = row;
		this.col = col;
		this.rack = rack;
		
		setTitle("Edit button action");
		setResizable(false);
		getContentPane().setLayout(null);
		
		setSize(511, 410);
		
		JPanel butDownPanel = new JPanel();
		FlowLayout fl_butDownPanel = (FlowLayout) butDownPanel.getLayout();
		fl_butDownPanel.setAlignment(FlowLayout.LEFT);
		butDownPanel.setBorder(new TitledBorder(null, "On Button Press", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		butDownPanel.setBounds(10, 11, 238, 175);
		getContentPane().add(butDownPanel);
		
		JPanel butUpPanel = new JPanel();
		FlowLayout fl_butUpPanel = (FlowLayout) butUpPanel.getLayout();
		fl_butUpPanel.setAlignment(FlowLayout.LEFT);
		butUpPanel.setBorder(new TitledBorder(null, "On Button Release", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		butUpPanel.setBounds(258, 11, 238, 175);
		getContentPane().add(butUpPanel);
		
		JPanel onEndPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) onEndPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		onEndPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"On Animaiton End", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		onEndPanel.setBounds(10, 197, 238, 175);
		getContentPane().add(onEndPanel);
		
		for (ButtonActionType type : ButtonActionType.values()) {
			buttonActionsDown[type.ordinal()] = new ButtonActionTypeRadioButton(type);
			buttonPressGroup.add(buttonActionsDown[type.ordinal()]);
			buttonPressGroup.setSelected(buttonActionsDown[type.ordinal()].getModel(), true);
			butDownPanel.add(buttonActionsDown[type.ordinal()]);
			
			buttonActionsUp[type.ordinal()] = new ButtonActionTypeRadioButton(type);
			buttonUpGroup.add(buttonActionsUp[type.ordinal()]);
			buttonUpGroup.setSelected(buttonActionsUp[type.ordinal()].getModel(), true);
			butUpPanel.add(buttonActionsUp[type.ordinal()]);
			
			buttonActionsStop[type.ordinal()] = new ButtonActionTypeRadioButton(type);
			buttonStopGroup.add(buttonActionsStop[type.ordinal()]);
			buttonStopGroup.setSelected(buttonActionsStop[type.ordinal()].getModel(), true);
			onEndPanel.add(buttonActionsStop[type.ordinal()]);
		}
		
		JPanel aniPanel = new JPanel();
		aniPanel.setBorder(new TitledBorder(null, "Animaiton", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		aniPanel.setBounds(258, 197, 238, 175);
		getContentPane().add(aniPanel);
		aniPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(66, 17, 162, 20);
		aniPanel.add(textField);
		textField.setText("1000");
		textField.setColumns(10);
		
		JLabel lblSpeed = new JLabel("Speed");
		lblSpeed.setBounds(10, 20, 46, 14);
		aniPanel.add(lblSpeed);
		
		lblSelectAnimation = new JLabel("Select Animation:");
		lblSelectAnimation.setBounds(10, 45, 127, 14);
		aniPanel.add(lblSelectAnimation);
		
		aniSelector = new JComboBox<Animation>(new DefaultComboBoxModel<>(
				Data.animations.toArray(new Animation[Data.animations.size()])));
		aniSelector.setBounds(10, 67, 218, 20);
		aniPanel.add(aniSelector);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(10, 98, 103, 23);
		btnSave.addActionListener(saveAction);
		aniPanel.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(125, 98, 103, 23);
		aniPanel.add(btnCancel);
		
		JButton btnRemoveTheAnimatino = new JButton("Remove the action");
		btnRemoveTheAnimatino.setBounds(10, 132, 218, 33);
		aniPanel.add(btnRemoveTheAnimatino);
		
		setSelectedActionButtonTypeDown(action.getPressType());
		setSelectedActionButtonTypeUp(action.getReleaseType());
		setSelectedActionButtonTypeStop(action.getAniStopType());	
		
	}
	
	private void setSelectedActionButtonTypeDown(ButtonActionType e) {
		buttonPressGroup.clearSelection();
		buttonActionsDown[e.ordinal()].setSelected(true);
	}
	
	private void setSelectedActionButtonTypeUp(ButtonActionType e) {
		buttonUpGroup.clearSelection();
		buttonActionsUp[e.ordinal()].setSelected(true);
	}
	
	private void setSelectedActionButtonTypeStop(ButtonActionType e) {
		buttonStopGroup.clearSelection();
		buttonActionsStop[e.ordinal()].setSelected(true);
	}
	
	private ButtonActionType getSelectedActionButtonTypeDown() {
		for (int i = 0; i < buttonActionsDown.length; i++) {
			if (buttonActionsDown[i].isSelected())
				return buttonActionsDown[i].getType();
		}
		// should never hapen :)
		return null;
	}
	
	private ButtonActionType getSelectedActionButtonTypeUp() {
		for (int i = 0; i < buttonActionsUp.length; i++) {
			if (buttonActionsUp[i].isSelected())
				return buttonActionsUp[i].getType();
		}
		// should never hapen :)
		return null;
	}
	
	private ButtonActionType getSelectedActionButtonTypeStop() {
		for (int i = 0; i < buttonActionsStop.length; i++) {
			if (buttonActionsStop[i].isSelected())
				return buttonActionsStop[i].getType();
		}
		// should never hapen :)
		return null;
	}
}
