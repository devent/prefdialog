package com.globalscalingsoftware.prefdialog.internal;

import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PreferencePanel extends JPanel {

	private final Field field;
	private final Object value;

	public PreferencePanel(Field field, Object value) {
		this.field = field;
		this.value = value;

		JButton button = new JButton(value.toString());
		add(button);
	}

}
