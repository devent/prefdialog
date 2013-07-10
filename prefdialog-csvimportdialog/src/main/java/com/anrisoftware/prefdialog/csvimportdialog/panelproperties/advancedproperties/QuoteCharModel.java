package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.advancedproperties;

import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.inject.Inject;
import javax.swing.DefaultComboBoxModel;

/**
 * Offers default quote characters.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class QuoteCharModel extends DefaultComboBoxModel<Character> {

	@Inject
	QuoteCharModel() {
		super(new Vector<Character>(getDefaults()));
	}

	private static Set<Character> getDefaults() {
		Set<Character> list = new TreeSet<Character>();
		list.add('"');
		return list;
	}

}
