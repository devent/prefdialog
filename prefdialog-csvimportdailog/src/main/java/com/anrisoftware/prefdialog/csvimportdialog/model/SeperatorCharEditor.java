package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;

public class SeperatorCharEditor implements ComboBoxEditor {

	private final ComboBoxEditor editor;

	SeperatorCharEditor() {
		this.editor = new JComboBox<Object>().getEditor();
	}

	@Override
	public Component getEditorComponent() {
		return editor.getEditorComponent();
	}

	@Override
	public void setItem(Object anObject) {
		editor.setItem(anObject);
	}

	@Override
	public Object getItem() {
		if (editor.getItem() != null) {
			return editor.getItem().toString().charAt(0);
		} else {
			return null;
		}
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
