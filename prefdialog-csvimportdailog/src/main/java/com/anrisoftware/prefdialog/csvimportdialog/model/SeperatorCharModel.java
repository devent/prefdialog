package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.inject.Inject;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class SeperatorCharModel extends DefaultComboBoxModel<Character> {

	@Inject
	SeperatorCharModel() {
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
