package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.advancedproperties;

import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.inject.Inject;
import javax.swing.DefaultComboBoxModel;

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
