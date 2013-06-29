package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.separatorproperties;

import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.inject.Inject;
import javax.swing.DefaultComboBoxModel;

/**
 * Sets the standard separator characters.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class SeparatorCharModel extends DefaultComboBoxModel<Character> {

	@Inject
	SeparatorCharModel() {
		super(new Vector<Character>(getSeperatorCharDefaults()));
	}

	private static Set<Character> getSeperatorCharDefaults() {
		Set<Character> list = new TreeSet<Character>();
		list.add('\t');
		list.add(',');
		list.add(';');
		list.add(' ');
		return list;
	}

}
