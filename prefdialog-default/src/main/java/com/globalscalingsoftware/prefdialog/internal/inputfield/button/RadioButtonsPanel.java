package com.globalscalingsoftware.prefdialog.internal.inputfield.button;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class RadioButtonsPanel extends JPanel {

	private final TableLayout layout;

	private final ButtonGroup buttonsGroup;

	private final Map<ButtonModel, Object> values;

	public RadioButtonsPanel() {
		double[] col = { TableLayout.PREFERRED };
		double[] row = {};
		layout = new TableLayout(col, row);
		buttonsGroup = new ButtonGroup();
		values = new HashMap<ButtonModel, Object>();
		setupPanel();
	}

	private void setupPanel() {
		setLayout(layout);
	}

	public Object getValue() {
		ButtonModel selected = buttonsGroup.getSelection();
		Object value = values.get(selected);
		return value;
	}

	public void addRadioButton(Object value, String text) {
		int index = layout.getNumRow();
		layout.insertRow(index, TableLayout.PREFERRED);
		JRadioButton button = new JRadioButton(text);
		values.put(button.getModel(), value);
		buttonsGroup.add(button);
		add(button, format("0, %d", index));
	}

	public void setValue(Object value) {
		for (Map.Entry<ButtonModel, Object> entry : values.entrySet()) {
			if (entry.getValue() == value) {
				entry.getKey().setSelected(true);
				break;
			}
		}
	}

}
