package com.sinius15.lights.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.sinius15.lights.Option;

public class EffectSettingsFrame extends JFrameLayered {
	
	private static final long serialVersionUID = -6846364234459877317L;
	private JPanel contentPane;
	
	public EffectSettingsFrame(Option<?>... options ) {
		setTitle("Effect Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Effect Settings", TitledBorder.LEADING, TitledBorder.TOP));
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		int y = 0;
		for(Option<?> option : options){
			JLabel label = new JLabel(option.getTitle());
			GridBagConstraints labelGbc = new GridBagConstraints();
			labelGbc.ipadx = 20;
			labelGbc.anchor = GridBagConstraints.NORTHWEST;
			labelGbc.insets = new Insets(0, 0, 5, 5);
			labelGbc.gridx = 0;
			labelGbc.gridy = y;
			panel.add(label, labelGbc);
			
			GridBagConstraints compSpinner = new GridBagConstraints();
			compSpinner.anchor = GridBagConstraints.NORTHWEST;
			compSpinner.insets = new Insets(0, 0, 5, 0);
			compSpinner.gridx = 1;
			compSpinner.gridy = y;
			panel.add(option.getComponent(), compSpinner);
			
			y++;
		}
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnSave, BorderLayout.SOUTH);
		
		pack();
	}
	
}
