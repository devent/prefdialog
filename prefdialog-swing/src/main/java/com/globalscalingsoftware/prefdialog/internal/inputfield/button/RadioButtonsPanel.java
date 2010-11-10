package com.globalscalingsoftware.prefdialog.internal.inputfield.button;

import static java.lang.String.format;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractLabelFieldPanel;

public class RadioButtonsPanel extends AbstractLabelFieldPanel<JPanel> {

	private final GridLayout layout;

	private final ButtonGroup buttonsGroup;

	private final Map<ButtonModel, Object> buttons;

	private String name;

	public RadioButtonsPanel() {
		super(new JPanel());
		layout = new GridLayout(0, 1);
		buttonsGroup = new ButtonGroup();
		buttons = new HashMap<ButtonModel, Object>();
		setupPanel();
	}

	private void setupPanel() {
		getField().setLayout(layout);
	}

	@Override
	public Object getValue() {
		ButtonModel selected = buttonsGroup.getSelection();
		Object value = buttons.get(selected);
		return value;
	}

	public void addRadioButton(Object value, String text) {
		int rows = layout.getRows();
		layout.setRows(rows + 1);
		JRadioButton button = new JRadioButton(text);
		button.setName(format("%s-%s", name, text));
		buttons.put(button.getModel(), value);
		buttonsGroup.add(button);
		getField().add(button);
	}

	@Override
	public void setName(String name) {
		this.name = name;
		super.setName(name);
	}

	@Override
	public void setValue(Object value) {
		for (Map.Entry<ButtonModel, Object> entry : buttons.entrySet()) {
			if (entry.getValue() == value) {
				entry.getKey().setSelected(true);
				break;
			}
		}
	}

	public void setColumns(int columns) {
		int rows = layout.getRows() / columns;
		layout.setRows(rows);
	}

}