package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.Set;

import javax.swing.ComboBoxEditor;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileNameEditor;

public class DefaultFileNameEditor implements FileNameEditor {

	private ComboBoxEditor editorDelegate;

	private final FileNameFormat format;

	private final JFormattedTextField field;

	public DefaultFileNameEditor() {
		this.format = new FileNameFormat();
		this.field = new JFormattedTextField(new DefaultFormatterFactory(
				new DefaultFormat(), new DefaultFormat(), new EditFormat()));
	}

	@Override
	public void setCurrentDirectory(File file) {
		format.setCurrentDirectory(file);
	}

	@Override
	public void setEditorDelegate(ComboBoxEditor editor) {
		this.editorDelegate = editor;
	}

	@Override
	public Component getEditorComponent() {
		JComponent component = (JComponent) editorDelegate.getEditorComponent();
		field.setBorder(component.getBorder());
		field.setMinimumSize(component.getMinimumSize());
		field.setMaximumSize(component.getMaximumSize());
		field.setPreferredSize(component.getPreferredSize());
		return field;
	}

	@Override
	public void setItem(Object anObject) {
		field.setValue(anObject);
	}

	@Override
	public Object getItem() {
		return field.getValue();
	}

	@Override
	public void selectAll() {
		editorDelegate.selectAll();
	}

	@Override
	public void addActionListener(ActionListener l) {
		field.addActionListener(l);
	}

	@Override
	public void removeActionListener(ActionListener l) {
		field.removeActionListener(l);
	}

	@SuppressWarnings("serial")
	private class DefaultFormat extends DefaultFormatter {

		@Override
		public String valueToString(Object value) throws ParseException {
			return format.format(value);
		}

		@Override
		public Class<?> getValueClass() {
			return Set.class;
		}

		@Override
		public boolean getAllowsInvalid() {
			return false;
		}
	}

	@SuppressWarnings("serial")
	private class EditFormat extends DefaultFormat {

		@Override
		public Object stringToValue(String string) throws ParseException {
			return format.parseObject(string);
		}

	}
}
