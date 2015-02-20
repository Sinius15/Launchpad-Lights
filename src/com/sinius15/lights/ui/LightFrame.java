package com.sinius15.lights.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sinius15.lights.FileLoader;
import com.sinius15.lights.Rack;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class LightFrame extends JFrameLayered {

	private static final long serialVersionUID = 2665207274016108428L;
	private JPanel contentPane;
	private LightFrame instance = this;

	public LightFrame(Rack rack) {
		setTitle("LightFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 328);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(saveListener);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(newListner);
		mnFile.add(mntmNew);
		mnFile.add(mntmSave);

		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(loadListener);
		mnFile.add(mntmLoad);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(rack, BorderLayout.CENTER);
		pack();
	}

	private ActionListener loadListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JFileChooser filechooser = new JFileChooser();
			filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			filechooser.setAcceptAllFileFilterUsed(true);
			filechooser.setDialogType(JFileChooser.OPEN_DIALOG);
			int returnErrorNR = filechooser.showSaveDialog(instance);
			if(returnErrorNR != 0)
				return;
			String path = filechooser.getSelectedFile().getAbsolutePath();
			FileLoader.LoadFile(new File(path));
		}
	};

	private ActionListener saveListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JFileChooser filechooser = new JFileChooser();
			filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			filechooser.setAcceptAllFileFilterUsed(true);
			filechooser.setDialogType(JFileChooser.SAVE_DIALOG);
			int returnErrorNR = filechooser.showSaveDialog(instance);
			if(returnErrorNR != 0)
				return;
			String path = filechooser.getSelectedFile().getAbsolutePath();
			FileLoader.SaveFile(new File(path));
		}
	};

	private ActionListener newListner = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			FileLoader.emptyProject();
		}
	};

}
