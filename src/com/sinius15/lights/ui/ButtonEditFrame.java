package com.sinius15.lights.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import com.sinius15.lights.Effect;
import com.sinius15.lights.LaunchpadLightCreator;
import com.sinius15.lights.Option;
import com.sinius15.lights.effects.NoneEffect;

public class ButtonEditFrame extends JFrameLayered {

	private static final long serialVersionUID = 6776358648201227305L;
	
	private JPanel contentPane;
	private JList<Effect> list;
	
	int row, col;
	
	public ButtonEditFrame(int row_, int col_) {
		this.row = row_;
		this.col = col_;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 433, 407);
		setTitle("Efect Selector");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.8);
		panel.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		Effect curEffect = LaunchpadLightCreator.rack.effects[row][col];
		int selectedListItem = 0;
		
		Effect[] effects = new Effect[LaunchpadLightCreator.effects.size()];
		
		for(int i = 0; i < LaunchpadLightCreator.effects.size(); i++){
			Class<? extends Effect> effectClass = LaunchpadLightCreator.effects.get(i);
			if(curEffect != null && curEffect.getClass().equals(effectClass)){
				effects[i] = curEffect;
				selectedListItem = i;
			}else{
				effects[i] = LaunchpadLightCreator.createIntance(effectClass, row, col);
			}
		}
		list = new JList<Effect>();
		list = new JList<Effect>(effects);
		
		list.setSelectedIndex(selectedListItem);
		scrollPane.setViewportView(list);
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		
		JLabel lblDescription = new JLabel("Description");
		panel_2.add(lblDescription);
		
		JButton btnEffectPreferences = new JButton("Effect Settings");
		btnEffectPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Effect eff = list.getSelectedValue();
				Option<?>[] options = eff.getOptions();
				if(options != null)
					new EffectSettingsFrame(options).setVisible(true);
			}
		});
		panel_2.add(btnEffectPreferences);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Effect newEffect = list.getSelectedValue();
				if(newEffect.getClass().equals(NoneEffect.class))
					newEffect = null;
				LaunchpadLightCreator.rack.effects[row][col] = newEffect;
				LaunchpadLightCreator.rack.buttons[row][col].colorStandardColor();
				dispose();
			}
		});
		panel_2.add(btnSave);
	}
	
}
