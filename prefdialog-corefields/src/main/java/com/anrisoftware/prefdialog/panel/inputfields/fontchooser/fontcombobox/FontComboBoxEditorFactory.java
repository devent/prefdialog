package com.anrisoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox;

import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;

public interface FontComboBoxEditorFactory {

	ComboBoxEditor create(ComboBoxModel model);
}
