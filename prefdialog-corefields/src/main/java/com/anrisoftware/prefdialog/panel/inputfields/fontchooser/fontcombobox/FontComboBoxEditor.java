package com.anrisoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox;

import static java.lang.Math.max;
import static java.lang.Math.min;

import javax.swing.ComboBoxModel;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Editor with uses the {@link AutoCompletionDocument} document.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class FontComboBoxEditor extends BasicComboBoxEditor {

	private final ComboBoxModel model;

	@Inject
	FontComboBoxEditor(@Assisted ComboBoxModel model) {
		this.model = model;
		editor.setDocument(new AutoCompletionDocument(editor, model));
		editor.setText(getSelectedItemAsFontsModelItem().getFont().getName());
	}

	private FontsModelItem getSelectedItemAsFontsModelItem() {
		return (FontsModelItem) model.getSelectedItem();
	}

	public void replaceSelection(String s) {
		AutoCompletionDocument doc = getAutoCompletionDocument();
		try {
			Caret caret = editor.getCaret();
			int i = min(caret.getDot(), caret.getMark());
			int j = max(caret.getDot(), caret.getMark());
			doc.replace(i, j - i, s, null);
		} catch (BadLocationException ex) {
		}
	}

	private AutoCompletionDocument getAutoCompletionDocument() {
		return (AutoCompletionDocument) editor.getDocument();
	}
}
