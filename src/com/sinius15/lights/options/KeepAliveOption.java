package com.sinius15.lights.options;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sinius15.lights.Option;

public class KeepAliveOption extends Option<Integer> implements ChangeListener{

	private JPanel panel;
	private JCheckBox checkBox;
	private JSpinner spinner;
	
	private JLabel label;
	
	public KeepAliveOption(){
		panel = new JPanel();
		checkBox = new JCheckBox();
		
		checkBox.addChangeListener(this);
		
		spinner = new JSpinner(new SpinnerNumberModel(5000, 1, null, 1));
		JComponent editor = spinner.getEditor();
		JFormattedTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
		tf.setColumns(7);
		
		
		label = new JLabel("Time (ms)");
		
		panel.add(checkBox);
	}
	
	@Override
	public JComponent getComponent() {
		return panel;
	}

	@Override
	public String getTitle() {
		return "<html>Keep pattern visable if the launchapd <br>button is released?</html>";
	}

	/**
	 * @return -1 if the checkbox is not selected. 
	 * Returns the selected value if the checkbox is selected.<br><br>
	 * So -1 if the pattern should hide on button release.
	 * And a number >=0 if he should not hide when button release
	 */
	@Override
	public Integer getValue() {
		return checkBox.isSelected() ? (int)spinner.getValue() : -1;
	}

	private boolean isAdded = true;
	@Override
	public void stateChanged(ChangeEvent e) {
		if(!isAdded && checkBox.isSelected()){
			panel.add(label);
			panel.add(spinner);
			panel.revalidate();
			isAdded = true;
		}
		if(isAdded && !checkBox.isSelected()){
			panel.remove(label);
			panel.remove(spinner);
			panel.revalidate();
			isAdded = false;
		}
	}

	@Override
	public String getSaveData() {
		return Integer.toString(getValue());
	}

	@Override
	public void initFromSaveData(String saveData) {
		int in = Integer.valueOf(saveData);
		if(in  == -1){
			checkBox.setSelected(false);
		}else{
			checkBox.setSelected(true);
			spinner.setValue(in);
		}
	}


}
