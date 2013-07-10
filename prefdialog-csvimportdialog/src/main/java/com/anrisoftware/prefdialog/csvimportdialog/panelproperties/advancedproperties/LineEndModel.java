package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.advancedproperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

/**
 * Offers default line end symbols.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class LineEndModel extends DefaultComboBoxModel<Object> {

	LineEndModel() {
		super(new Vector<Object>(getDefaults()));
	}

	private static List<Object> getDefaults() {
		List<Object> list = new ArrayList<Object>();
		list.add(LineEnd.DEFAULT);
		list.add(LineEnd.UNIX);
		list.add(LineEnd.WINDOWS);
		return list;
	}

}
