package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;

public class CustomSeparatorCharEditor implements ComboBoxEditor {

	private final ComboBoxEditor editor;

	CustomSeparatorCharEditor() {
		this.editor = new JComboBox<Object>().getEditor();
	}

	@Override
	public Component getEditorComponent() {
		return editor.getEditorComponent();
	}

	@Override
	public void setItem(Object item) {
		if (item instanceof Character) {
			checkNullCharacter((Character) item);
		} else {
			editor.setItem(item);
		}
	}

	private void checkNullCharacter(Character c) {
		if (c.charValue() == '\0') {
			editor.setItem(null);
		} else {
			editor.setItem(c);
		}
	}

	@Override
	public Object getItem() {
		Object item = editor.getItem();
		if (item != null) {
			String string = item.toString();
			if (string.length() > 0) {
				item = string.charAt(0);
			} else {
				item = null;
			}
		}
		return item;
	}

	@Override
	public void selectAll() {
		editor.selectAll();
	}

	@Override
	public void addActionListener(ActionListener l) {
		editor.addActionListener(l);
	}

	@Override
	public void removeActionListener(ActionListener l) {
		editor.removeActionListener(l);
	}

}
