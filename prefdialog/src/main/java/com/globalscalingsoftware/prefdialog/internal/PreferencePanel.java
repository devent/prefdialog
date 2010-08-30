package com.globalscalingsoftware.prefdialog.internal;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PreferencePanel {

	private final TableLayout layout;
	private final UiPreferencePanel uiPreferencePanel;
	private final Map<String, InputField> inputFields;
	private final ReflectionToolbox reflectionToolbox;
	private Runnable applyEvent;

	public PreferencePanel(ReflectionToolbox reflectionToolbox) {
		this.reflectionToolbox = reflectionToolbox;
		uiPreferencePanel = new UiPreferencePanel();
		inputFields = new HashMap<String, InputField>();

		double[] cols = { TableLayout.PREFERRED, TableLayout.FILL, 0.6 };
		double[] rows = {};
		layout = new TableLayout(cols, rows);
		uiPreferencePanel.getBottomPanel().setLayout(layout);
		setupActions();
	}

	public JPanel getPanel() {
		return uiPreferencePanel;
	}

	private void setupActions() {
		uiPreferencePanel.getApplyButton().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (applyEvent != null) {
							applyEvent.run();
						}
					}
				});
	}

	public void setApplyEvent(Runnable applyEvent) {
		this.applyEvent = applyEvent;
	}

	public void setTitle(String title) {
		uiPreferencePanel.getChildTitleLabel().setText(title);
	}

	public void addFormattedTextField(Object parentObject, Field field,
			Object value) {
		int index = layout.getNumRow();
		layout.insertRow(index, TableLayout.PREFERRED);
		final JFormattedTextField textfield = new JFormattedTextField();
		inputFields.put(field.getName(), new InputField(reflectionToolbox,
				textfield, parentObject, field));
		textfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent ev) {
				try {
					textfield.commitEdit();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		JLabel label = new JLabel(field.getName() + ": ");
		label.setLabelFor(textfield);
		uiPreferencePanel.getBottomPanel().add(label, format("0, %d", index));
		uiPreferencePanel.getBottomPanel().add(textfield,
				format("2, %d", index));

		textfield.setValue(value);
	}

	public void addTextField(final Object parentObject, final Field field,
			Object value) {
		int index = layout.getNumRow();
		layout.insertRow(index, TableLayout.PREFERRED);
		final JTextField textfield = new JTextField();
		inputFields.put(field.getName(), new InputField(reflectionToolbox,
				textfield, parentObject, field));
		textfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent ev) {
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		JLabel label = new JLabel(field.getName() + ": ");
		label.setLabelFor(textfield);
		uiPreferencePanel.getBottomPanel().add(label, format("0, %d", index));
		uiPreferencePanel.getBottomPanel().add(textfield,
				format("2, %d", index));

		if (value != null) {
			textfield.setText(value.toString());
		}
	}

	public void setApplyAction(Action a) {
		uiPreferencePanel.getApplyButton().setAction(a);
	}

	public void setRestoreAction(Action a) {
		uiPreferencePanel.getRestoreButton().setAction(a);
	}

	public void applyAllInput() {
		for (InputField inputField : inputFields.values()) {
			inputField.applyInput();
		}
	}

	public void undoAllInput() {
		// TODO Auto-generated method stub

	}
}
