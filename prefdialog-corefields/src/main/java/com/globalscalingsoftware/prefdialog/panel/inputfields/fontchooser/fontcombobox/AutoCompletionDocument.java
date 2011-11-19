package com.globalscalingsoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox;

import javax.swing.ComboBoxModel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.google.common.base.Strings;

/**
 * Plain text document for the text area. Needed for text selection.
 * 
 * Inspired by http://www.java2s.com/Code/Java/Swing-Components/
 * AutocompleteComboBox.htm
 * 
 * @author Andreas Wenger
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
@SuppressWarnings("serial")
class AutoCompletionDocument extends PlainDocument {

	private final JTextField textField;

	private final ComboBoxModel model;

	public AutoCompletionDocument(JTextField textField, ComboBoxModel model) {
		this.textField = textField;
		this.model = model;
	}

	@Override
	public void replace(int i, int j, String s, AttributeSet attributeset)
			throws BadLocationException {
		super.remove(i, j);
		insertString(i, s, attributeset);
	}

	@Override
	public void insertString(int i, String s, AttributeSet attributeset)
			throws BadLocationException {
		if (!Strings.isNullOrEmpty(s)) {
			String s1 = getText(0, i);
			String s2 = getMatch(s1 + s);
			int j = (i + s.length()) - 1;
			if (s2 == null) {
				s2 = getMatch(s1);
				j--;
			}
			if (s2 != null) {
				model.setSelectedItem(s2);
			}
			super.remove(0, getLength());
			super.insertString(0, s2, attributeset);
			textField.setSelectionStart(j + 1);
			textField.setSelectionEnd(getLength());
		}
	}

	@Override
	public void remove(int i, int j) throws BadLocationException {
		int k = textField.getSelectionStart();
		if (k > 0)
			k--;
		String s = getMatch(getText(0, k));

		super.remove(0, getLength());
		super.insertString(0, s, null);

		if (s != null)
			model.setSelectedItem(s);
		try {
			textField.setSelectionStart(k);
			textField.setSelectionEnd(getLength());
		} catch (Exception exception) {
		}
	}

	private String getMatch(String input) {
		for (int i = 0; i < model.getSize(); i++) {
			String name = getElementAtAsFontsModelItem(i).getFont().getName();
			if (name.toLowerCase().startsWith(input.toLowerCase())) {
				return name;
			}
		}
		return null;
	}

	private FontsModelItem getElementAtAsFontsModelItem(int i) {
		return (FontsModelItem) model.getElementAt(i);
	}

}
