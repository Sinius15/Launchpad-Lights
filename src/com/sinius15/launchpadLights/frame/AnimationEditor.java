package com.sinius15.launchpadLights.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import com.sinius15.launchpad.Launchpad;
import com.sinius15.launchpad.pattern.LaunchpadPattern;
import com.sinius15.launchpad.pattern.VisualLaunchpadPatternFactory;
import com.sinius15.launchpadLights.Animation;
import com.sinius15.launchpadLights.Data;
import com.sinius15.launchpadLights.util.LaunchpadPatternCellRenderer;

public class AnimationEditor extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private AnimationEditor thiss;
	
	private Animation ani;
	private int index;
	private JList<LaunchpadPattern> list;
	
	public AnimationEditor(Animation a, int indexInData) {
		this.ani = a;
		this.thiss = this;
		this.index = indexInData;
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				LightFrame.thiss.setEnabled(true);
				LightFrame.thiss.requestFocus();
			}
		});
		
		setTitle("Animation Editor");
		getContentPane().setLayout(null);
		setBounds(0, 0, 296, 368);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 129, 252);
		getContentPane().add(scrollPane);
		
		list = new JList<LaunchpadPattern>();
		list.setCellRenderer(new LaunchpadPatternCellRenderer());
		scrollPane.setViewportView(list);
		
		JLabel lblLaunchpadPatterns = new JLabel("Launchpad Patterns");
		lblLaunchpadPatterns.setBounds(10, 10, 200, 14);
		getContentPane().add(lblLaunchpadPatterns);
		
		JButton btnMoveUp = new JButton("Move Up");
		btnMoveUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isSelectedAndGiveError())
					return;
				int curIndex = list.getSelectedIndex();
				if(curIndex == 0)
					return;
				LaunchpadPattern p = ani.animations[curIndex];
				ani.animations[curIndex] = ani.animations[curIndex-1];
				ani.animations[curIndex-1] = p;
				updateDataToList();
				list.setSelectedIndex(curIndex-1);
			}
		});
		btnMoveUp.setBounds(149, 28, 104, 23);
		getContentPane().add(btnMoveUp);
		
		JButton btnMoveDown = new JButton("Move Down");
		btnMoveDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isSelectedAndGiveError())
					return;
				int curIndex = list.getSelectedIndex();
				if(curIndex+1 == ani.animations.length)
					return;
				LaunchpadPattern p = ani.animations[curIndex];
				ani.animations[curIndex] = ani.animations[curIndex+1];
				ani.animations[curIndex+1] = p;
				updateDataToList();
				list.setSelectedIndex(curIndex+1);
			}
		});
		btnMoveDown.setBounds(149, 62, 104, 23);
		getContentPane().add(btnMoveDown);
		
		JButton btnTestShow = new JButton("Test Pattern");
		btnTestShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isSelectedAndGiveError())
					return;
				Launchpad pad = LightFrame.getLaunchpad();
				pad.showPattern(list.getSelectedValue());
				JOptionPane.showMessageDialog(thiss, "Now showing the selected pattern. "+System.lineSeparator()+" Press ok to clear the pad.", "displaying...", JOptionPane.INFORMATION_MESSAGE);
				pad.reset();
			}
		});
		btnTestShow.setBounds(149, 109, 104, 23);
		getContentPane().add(btnTestShow);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(thiss, "Enter the name of the new Launchapd-Pattern", "New Launchpad Pattern", JOptionPane.QUESTION_MESSAGE);
				if(name == null || name.equals(""))
					return;
				ani.animations = Arrays.copyOf(ani.animations, ani.animations.length+1);
				ani.animations[ani.animations.length-1] = new LaunchpadPattern();
				ani.animations[ani.animations.length-1].setName(name);
				editPattern(LightFrame.getLaunchpad(), ani.animations.length-1);
				
				updateDataToList();
			}
		});
		btnNew.setBounds(149, 189, 104, 23);
		getContentPane().add(btnNew);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!isSelectedAndGiveError())
					return;
				editPattern(LightFrame.getLaunchpad(), list.getSelectedIndex());
			}
		});
		btnEdit.setBounds(149, 223, 104, 23);
		getContentPane().add(btnEdit);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!isSelectedAndGiveError())
					return;
				int curIndex = list.getSelectedIndex();
				List<LaunchpadPattern> arrList = new ArrayList<>(Arrays.asList(ani.animations));
				arrList.remove(curIndex);
				ani.animations = arrList.toArray(new LaunchpadPattern[arrList.size()]);
				updateDataToList();
			}
		});
		btnRemove.setBounds(149, 257, 104, 23);
		getContentPane().add(btnRemove);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data.animations.remove(index);
				Data.animations.add(index, ani);
				thiss.dispose();
				thiss.getWindowListeners()[0].windowClosing(null);
			}
		});
		btnSave.setBounds(10, 299, 129, 23);
		getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thiss.dispose();
				thiss.getWindowListeners()[0].windowClosing(null);
			}
		});
		btnCancel.setBounds(149, 299, 104, 23);
		getContentPane().add(btnCancel);
		
		JButton btnTestShow_1 = new JButton("Test Show");
		btnTestShow_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				float speed;
				try{
					speed = Float.parseFloat(JOptionPane.showInputDialog(thiss, "What time does it need to wait between different launchpad patterns?", "Speed?", JOptionPane.QUESTION_MESSAGE));
				}catch(Exception e2){
					return;
				}
				for(LaunchpadPattern p : ani.animations){
					LightFrame.getLaunchpad().showPattern(p);
					try {
						Thread.sleep((long) (speed*1000));
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				LightFrame.getLaunchpad().reset();
			}
		});
		btnTestShow_1.setBounds(149, 143, 104, 23);
		getContentPane().add(btnTestShow_1);
		
		updateDataToList();
	}
	
	public void updateDataToList(){
		list.setListData(ani.animations);
	}
	
	//pad must already be opend and will not be closed
	public void editPattern(Launchpad pad, int patternIndex){
		VisualLaunchpadPatternFactory fac = new VisualLaunchpadPatternFactory(pad, ani.animations[patternIndex]);
		fac.startRecording();
		JOptionPane.showMessageDialog(this, fac, "Create your new launchapd pattern", JOptionPane.INFORMATION_MESSAGE);
		ani.animations[patternIndex] = fac.stopRecording();
		pad.reset();
	}
	
	//returns false when a error is throwen
	public boolean isSelectedAndGiveError(){
		if(list.getSelectedValue() == null){
			JOptionPane.showMessageDialog(this, "No launchpad-pattern selected.", "Sorry.", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
