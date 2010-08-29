package com.globalscalingsoftware.prefdialog.internal;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.TextField;

public class PreferencePanel {

	@SuppressWarnings("serial")
	public class PreferencePanelInteral extends JPanel {

		private final Object parentValue;

		private final TableLayout layout;

		public PreferencePanelInteral(Object parentValue) {
			double[] cols = { TableLayout.PREFERRED, TableLayout.FILL };
			double[] rows = {};
			layout = new TableLayout(cols, rows);
			setLayout(layout);
			this.parentValue = parentValue;
			discoverAnnotations(parentValue);
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
			JTextField textfield = new JTextField();
			JLabel label = new JLabel(field.getName());
			add(label, format("0, %d", index));
			add(textfield, format("1, %d", index));
		}
	}

	private final AnnotationDiscovery annotationDiscovery;
	private final AnnotationsFilter annotationsFilter;

	PreferencePanel(AnnotationDiscovery annotationDiscovery,
			AnnotationsFilter annotationsFilter) {
		this.annotationDiscovery = annotationDiscovery;
		this.annotationsFilter = annotationsFilter;
	}

	public JPanel createPanel(Field field, Object value) {
		return new PreferencePanelInteral(value);
	}

}
