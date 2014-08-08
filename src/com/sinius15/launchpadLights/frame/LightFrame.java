package com.sinius15.launchpadLights.frame;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.jsresources.MidiCommon;

import com.sinius15.launchpad.BufferedLaunchpad;
import com.sinius15.launchpad.pattern.LaunchpadPattern;
import com.sinius15.launchpadLights.Animation;
import com.sinius15.launchpadLights.Data;
import com.sinius15.launchpadLights.IOer;
import com.sinius15.launchpadLights.Rack;

public class LightFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public static LightFrame thiss;  //there will always be just 1 LightFrame
	
	private JList<Animation> animationsList;
	private BufferedLaunchpad pad = null;
	
	ActionListener newAnimation = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String name = JOptionPane.showInputDialog(thiss, "Enter the name of the new animation.", "New animation", JOptionPane.QUESTION_MESSAGE);
			if(name == null || name.equals(""))
				return;
			Data.animations.add(new Animation(name, new LaunchpadPattern[0]));
			updateAnimatinoList();
		}
	};
	ActionListener removeAnimation = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Animation val = animationsList.getSelectedValue();
			if(val == null){
				JOptionPane.showMessageDialog(thiss, "No animation selected.", "Nothing selected.", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int returner = JOptionPane.showConfirmDialog(thiss, "Are you sure you want to remove this animation?", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(returner == JOptionPane.YES_OPTION)
				Data.animations.remove(val);
			updateAnimatinoList();
		}
	};
	
	ActionListener editAnimation = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Animation val = animationsList.getSelectedValue();
			if(val == null){
				JOptionPane.showMessageDialog(thiss, "No animation selected.", "Nothing selected.", JOptionPane.ERROR_MESSAGE);
				return;
			}
			AnimationEditor frame = new AnimationEditor(val, animationsList.getSelectedIndex());
			frame.setVisible(true);
			frame.setLocationRelativeTo(thiss);
			thiss.setEnabled(false);
			frame.requestFocus();
		}
	};
	ActionListener addRack = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String name = JOptionPane.showInputDialog(thiss, "Enter the name of the new Launchapd-Pattern", "New Launchpad Pattern", JOptionPane.QUESTION_MESSAGE);
			if(name == null || name.equals(""))
				return;
			Data.racks.put(name, new Rack());
			updateRacks();
		}
	};
	
	ActionListener saveToFileAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			IOer.saveAllToFile(new File(txtSaveFile.getText()));
		}
	};
	ActionListener loadFromFileAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			IOer.loadAllFromFile(new File(txtLoadFile.getText()));
		}
	};
	
	private JTabbedPane rackTabs;
	private JTabbedPane mainTabs;
	private JTextField txtSaveFile;
	private JTextField txtLoadFile;
	
	
	public LightFrame(String lpName) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(getLaunchpad() != null)
					getLaunchpad().close();
			}
		});
		thiss = this;
		
		setBounds(1000, 100, 862, 590);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		mainTabs = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(mainTabs, BorderLayout.CENTER);
		
		JPanel generalSettingsPanel = new JPanel();
		mainTabs.addTab("General Settings", null, generalSettingsPanel, null);
		GridBagLayout gbl_generalSettingsPanel = new GridBagLayout();
		gbl_generalSettingsPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_generalSettingsPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_generalSettingsPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_generalSettingsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		generalSettingsPanel.setLayout(gbl_generalSettingsPanel);
		
		JLabel lblSaveToFile = new JLabel("Save to File");
		GridBagConstraints gbc_lblSaveToFile = new GridBagConstraints();
		gbc_lblSaveToFile.anchor = GridBagConstraints.WEST;
		gbc_lblSaveToFile.insets = new Insets(0, 5, 5, 5);
		gbc_lblSaveToFile.gridx = 0;
		gbc_lblSaveToFile.gridy = 1;
		generalSettingsPanel.add(lblSaveToFile, gbc_lblSaveToFile);
		
		txtSaveFile = new JTextField();
		txtSaveFile.setText("U:\\saveFile.accdb");
		GridBagConstraints gbc_txtSaveFile = new GridBagConstraints();
		gbc_txtSaveFile.insets = new Insets(0, 0, 5, 5);
		gbc_txtSaveFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSaveFile.gridx = 1;
		gbc_txtSaveFile.gridy = 1;
		generalSettingsPanel.add(txtSaveFile, gbc_txtSaveFile);
		txtSaveFile.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(saveToFileAction);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 0);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 1;
		generalSettingsPanel.add(btnSave, gbc_btnSave);
		
		JLabel lblLoadFromFile = new JLabel("Load From File");
		GridBagConstraints gbc_lblLoadFromFile = new GridBagConstraints();
		gbc_lblLoadFromFile.insets = new Insets(0, 5, 0, 5);
		gbc_lblLoadFromFile.anchor = GridBagConstraints.WEST;
		gbc_lblLoadFromFile.gridx = 0;
		gbc_lblLoadFromFile.gridy = 2;
		generalSettingsPanel.add(lblLoadFromFile, gbc_lblLoadFromFile);
		
		txtLoadFile = new JTextField();
		txtLoadFile.setText("U:\\saveFile.accdb");
		GridBagConstraints gbc_txtLoadFile = new GridBagConstraints();
		gbc_txtLoadFile.insets = new Insets(0, 0, 0, 5);
		gbc_txtLoadFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLoadFile.gridx = 1;
		gbc_txtLoadFile.gridy = 2;
		generalSettingsPanel.add(txtLoadFile, gbc_txtLoadFile);
		txtLoadFile.setColumns(10);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(loadFromFileAction);
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.insets = new Insets(0, 0, 0, 5);
		gbc_btnLoad.gridx = 2;
		gbc_btnLoad.gridy = 2;
		generalSettingsPanel.add(btnLoad, gbc_btnLoad);
		
		JPanel racksPanel = new JPanel();
		mainTabs.addTab("Racks", null, racksPanel, null);
		racksPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel racksTopPanel = new JPanel();
		racksPanel.add(racksTopPanel, BorderLayout.NORTH);
		GridBagLayout gbl_racksTopPanel = new GridBagLayout();
		gbl_racksTopPanel.columnWidths = new int[]{0, 0, 0};
		gbl_racksTopPanel.rowHeights = new int[]{0, 0};
		gbl_racksTopPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_racksTopPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		racksTopPanel.setLayout(gbl_racksTopPanel);
		
		JButton btnNewRack = new JButton("New Rack");
		btnNewRack.addActionListener(addRack);
		GridBagConstraints gbc_btnNewRack = new GridBagConstraints();
		gbc_btnNewRack.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewRack.gridx = 0;
		gbc_btnNewRack.gridy = 0;
		racksTopPanel.add(btnNewRack, gbc_btnNewRack);
		
		JButton btnRemoveRack = new JButton("Remove Rack");
		GridBagConstraints gbc_btnRemoveRack = new GridBagConstraints();
		gbc_btnRemoveRack.insets = new Insets(5, 5, 5, 5);
		gbc_btnRemoveRack.gridx = 1;
		gbc_btnRemoveRack.gridy = 0;
		racksTopPanel.add(btnRemoveRack, gbc_btnRemoveRack);
		
		rackTabs = new JTabbedPane(JTabbedPane.LEFT);
		racksPanel.add(rackTabs, BorderLayout.CENTER);
		
		JPanel animatinosPanel = new JPanel();
		mainTabs.addTab("Animations", null, animatinosPanel, null);
		animatinosPanel.setLayout(null);
		
		JScrollPane animatinosListScroller = new JScrollPane();
		animatinosListScroller.setBounds(40, 40, 305, 271);
		animatinosPanel.add(animatinosListScroller);
		
		animationsList = new JList<Animation>();
		animatinosListScroller.setViewportView(animationsList);
		
		JButton btnNewAnimation = new JButton("New");
		btnNewAnimation.addActionListener(newAnimation);
		btnNewAnimation.setBounds(40, 322, 96, 23);
		animatinosPanel.add(btnNewAnimation);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(editAnimation);
		btnEdit.setBounds(143, 322, 96, 23);
		animatinosPanel.add(btnEdit);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(removeAnimation);
		btnRemove.setBounds(249, 322, 96, 23);
		animatinosPanel.add(btnRemove);
		
		setLocationRelativeTo(null);
		
		updateAnimatinoList();
		
		try {
			thiss.pad = new BufferedLaunchpad(lpName);
			thiss.pad.open();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(thiss, "Could not open launchpad. Are you sure you selected the right launchpad?", "Could not open.", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
	}

	public static BufferedLaunchpad getLaunchpad(){
		return thiss.pad;
	}
	
	public void updateAnimatinoList(){
		animationsList.setListData(Data.animations.toArray(new Animation[Data.animations.size()]));
	}
	public void updateRacks(){
		rackTabs.removeAll();
		getLaunchpad().clearButtonListeners();
		for(String key : Data.racks.keySet()){
			rackTabs.addTab(key, Data.racks.get(key));
			getLaunchpad().addButtonListener(Data.racks.get(key));
		}
		
	}
	
	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
//			e1.printStackTrace();
//		}
		String[] options = MidiCommon.listDevices(true, true);
		String lpName = (String) JOptionPane.showInputDialog(null, "Choose launchap...", "chose your launchpad!", JOptionPane.QUESTION_MESSAGE, null, options,options[1]);
		if(lpName == null)
			return;
		
		
		Data.init();
		new LightFrame(lpName).setVisible(true);
	}
}
