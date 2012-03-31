package com.anrisoftware.prefdialog.panel

import java.awt.GraphicsEnvironment

import javax.swing.DefaultComboBoxModel

class FontNameModel extends DefaultComboBoxModel {

	FontNameModel() {
		def ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fontNames = ge.getAvailableFontFamilyNames();
		fontNames.each {it -> addElement(it)}
	}
}
