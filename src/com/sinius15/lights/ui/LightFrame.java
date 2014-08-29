package com.sinius15.lights.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sinius15.lights.Rack;

public class LightFrame extends JFrame {
	
	private static final long serialVersionUID = 2665207274016108428L;
	private JPanel contentPane;
	
	public Rack launchRack;
	
	public LightFrame() {
		setTitle("LightFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		launchRack = new Rack();
		contentPane.add(launchRack, BorderLayout.CENTER);
	}
	
}
