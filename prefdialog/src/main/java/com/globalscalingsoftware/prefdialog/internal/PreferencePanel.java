package com.globalscalingsoftware.prefdialog.internal;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.FormattedTextField;
import com.globalscalingsoftware.prefdialog.TextField;

public class PreferencePanel {

	private final TableLayout layout;
	private final UiPreferencePanel uiPreferencePanel;
	private final AnnotationDiscovery annotationDiscovery;
	private final AnnotationsFilter annotationsFilter;
	private final ReflectionToolbox reflectionToolbox;
	private final Map<String, InputField> inputFields;

	public PreferencePanel(AnnotationDiscovery annotationDiscovery,
			AnnotationsFilter annotationsFilter,
			ReflectionToolbox reflectionToolbox) {
		this.annotationDiscovery = annotationDiscovery;
		this.annotationsFilter = annotationsFilter;
		this.reflectionToolbox = reflectionToolbox;
		uiPreferencePanel = new UiPreferencePanel();
		inputFields = new HashMap<String, InputField>();

		double[] cols = { TableLayout.PREFERRED, TableLayout.FILL, 0.6 };
		double[] rows = {};
		layout = new TableLayout(cols, rows);
	}

	public JPanel getPanel(Field field, Object value) {
		setupChildTitle(value);
		setupLayout();
		discoverAnnotations(value);
		setupActions();
		return uiPreferencePanel;
	}

	private void setupActions() {
		uiPreferencePanel.getApplyButton().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						for (InputField inputField : inputFields.values()) {
							inputField.applyInput();
						}
					}
				});
	}

	private void setupChildTitle(Object value) {
		uiPreferencePanel.getChildTitleLabel().setText(value.toString());
	}

	private void setupLayout() {
		uiPreferencePanel.getBottomPanel().setLayout(layout);
	}

	private void discoverAnnotations(final Object parentObject) {
		annotationDiscovery.discover(parentObject, annotationsFilter,
				new DiscoveredListener() {

					@Override
					public void fieldAnnotationDiscovered(Field field,
							Object value, Annotation a) {
						if (a instanceof FormattedTextField) {
							createFormattedTextField(parentObject, field, value);
						} else if (a instanceof TextField) {
							createTextField(parentObject, field, value);
						}

					}
				});
	}

	private void createFormattedTextField(final Object parentObject,
			final Field field, Object object) {
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

		textfield.setValue(object);
	}

	private void createTextField(final Object parentObject, final Field field,
			Object object) {
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

		if (object != null) {
			textfield.setText(object.toString());
		}
	}

	public void setApplyAction(Action a) {
		uiPreferencePanel.getApplyButton().setAction(a);
	}

	public void setRestoreAction(Action a) {
		uiPreferencePanel.getRestoreButton().setAction(a);
	}
}
