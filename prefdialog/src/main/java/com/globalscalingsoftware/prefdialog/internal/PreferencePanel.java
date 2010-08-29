package com.globalscalingsoftware.prefdialog.internal;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.swing.Action;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.globalscalingsoftware.prefdialog.TextField;

public class PreferencePanel {

	private final TableLayout layout;
	private final UiPreferencePanel uiPreferencePanel;
	private final AnnotationDiscovery annotationDiscovery;
	private final AnnotationsFilter annotationsFilter;

	public PreferencePanel(AnnotationDiscovery annotationDiscovery,
			AnnotationsFilter annotationsFilter) {
		this.annotationDiscovery = annotationDiscovery;
		this.annotationsFilter = annotationsFilter;
		uiPreferencePanel = new UiPreferencePanel();

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

					}
				});
	}

	private void setupChildTitle(Object value) {
		uiPreferencePanel.getChildTitleLabel().setText(value.toString());
	}

	private void setupLayout() {
		uiPreferencePanel.getBottomPanel().setLayout(layout);
	}

	private void discoverAnnotations(Object value) {
		try {
			annotationDiscovery.discover(value, annotationsFilter,
					new DiscoveredListener() {

						@Override
						public void fieldAnnotationDiscovered(Field field,
								Object value, Annotation a) {
							if (a instanceof TextField) {
								createTextField(field, value);
							}

						}
					});
		} catch (AnnotationDiscoveryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createTextField(Field field, Object value) {
		int index = layout.getNumRow();
		layout.insertRow(index, TableLayout.PREFERRED);
		JFormattedTextField textfield = new JFormattedTextField();
		JLabel label = new JLabel(field.getName() + ": ");
		label.setLabelFor(textfield);
		uiPreferencePanel.getBottomPanel().add(label, format("0, %d", index));
		uiPreferencePanel.getBottomPanel().add(textfield,
				format("2, %d", index));

		textfield.setValue(value);
	}

	public void setApplyAction(Action a) {
		uiPreferencePanel.getApplyButton().setAction(a);
	}

	public void setRestoreAction(Action a) {
		uiPreferencePanel.getRestoreButton().setAction(a);
	}
}
