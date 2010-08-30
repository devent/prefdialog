package com.globalscalingsoftware.prefdialog.internal;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.IPreferencePanel;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;

public class PreferencePanel implements IPreferencePanel {

	private final TableLayout layout;
	private final UiPreferencePanel uiPreferencePanel;
	private final Map<String, InputField> inputFields;
	private final IReflectionToolbox reflectionToolbox;
	private final RunnableActionEvent applyEvent;

	public PreferencePanel(IReflectionToolbox reflectionToolbox) {
		this.reflectionToolbox = reflectionToolbox;
		uiPreferencePanel = new UiPreferencePanel();
		inputFields = new HashMap<String, InputField>();
		applyEvent = new RunnableActionEvent();

		double[] cols = { TableLayout.PREFERRED, TableLayout.FILL, 0.6 };
		double[] rows = {};
		layout = new TableLayout(cols, rows);
		uiPreferencePanel.getBottomPanel().setLayout(layout);
		setupActions();
	}

	@Override
	public JPanel getPanel() {
		return uiPreferencePanel;
	}

	private void setupActions() {
		uiPreferencePanel.getApplyButton().addActionListener(applyEvent);
	}

	@Override
	public void setTitle(String title) {
		uiPreferencePanel.getChildTitleLabel().setText(title);
	}

	@Override
	public void addFormattedTextField(Object parentObject, Field field,
			Object value) {
		final JFormattedTextField textfield = new JFormattedTextField();
		insertTextField(parentObject, field, textfield,
				new ValidatingFormattedTextField(textfield));

		textfield.setValue(value);
	}

	@Override
	public void addTextField(final Object parentObject, final Field field,
			Object value) {
		JTextField textfield = new JTextField();
		insertTextField(parentObject, field, textfield,
				new ValidatingTextField(textfield));

		if (value != null) {
			textfield.setText(value.toString());
		}
	}

	private void insertTextField(Object parentObject, Field field,
			JTextField textfield, FocusListener focusListener) {
		InputField inputField = new InputField(reflectionToolbox, textfield,
				parentObject, field);
		inputFields.put(field.getName(), inputField);

		textfield.addFocusListener(focusListener);
		JLabel label = new JLabel(field.getName() + ": ");
		label.setLabelFor(textfield);
		insertTextFieldIntoContainer(textfield, label);
	}

	private void insertTextFieldIntoContainer(JTextField textfield, JLabel label) {
		int index = layout.getNumRow();
		layout.insertRow(index, TableLayout.PREFERRED);
		uiPreferencePanel.getBottomPanel().add(label, format("0, %d", index));
		uiPreferencePanel.getBottomPanel().add(textfield,
				format("2, %d", index));
	}

	@Override
	public void setApplyAction(Action a) {
		uiPreferencePanel.getApplyButton().setAction(a);
	}

	@Override
	public void setApplyEvent(Runnable applyEvent) {
		this.applyEvent.setEvent(applyEvent);
	}

	@Override
	public void setRestoreAction(Action a) {
		uiPreferencePanel.getRestoreButton().setAction(a);
	}

	@Override
	public void applyAllInput() {
		for (InputField inputField : inputFields.values()) {
			inputField.applyInput();
		}
	}

	@Override
	public void undoAllInput() {
		// TODO Auto-generated method stub

	}
}
