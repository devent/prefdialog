package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;

public class CharsetEditor implements ComboBoxEditor {

	private final ComboBoxEditor editor;

	CharsetEditor() {
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
		Object item = editor.getItem();
		if (item != null) {
			try {
				return asCharset(item);
			} catch (UnsupportedCharsetException e) {
				return item;
			}
		} else {
			return null;
		}
	}

	private Charset asCharset(Object item) {
		return Charset.forName(item.toString());
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
