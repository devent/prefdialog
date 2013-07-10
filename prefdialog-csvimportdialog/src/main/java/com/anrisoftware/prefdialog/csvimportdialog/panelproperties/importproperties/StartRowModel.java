package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.importproperties;

import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class StartRowModel extends SpinnerNumberModel {

	StartRowModel() {
		super(0, 0, null, 1);
	}
}
