package com.globalscalingsoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox;

/**
 * Factory to create a new {@link FontComboBox}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public interface FontComboBoxFactory {

	FontComboBox create(String[] fontNames);
}
