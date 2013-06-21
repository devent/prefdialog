package com.anrisoftware.prefdialog.csvimportdialog.model;

import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class StartRowModel extends SpinnerNumberModel {

	StartRowModel() {
		super(0, 0, null, 1);
	}
}
