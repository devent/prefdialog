package com.anrisoftware.prefdialog.panel

import javax.swing.DefaultComboBoxModel

class FontSizeModel extends DefaultComboBoxModel {
	FontSizeModel() {
		super([
			8,
			9,
			10,
			11,
			12,
			14,
			16,
			18,
			20,
			22,
			24,
			26,
			28,
			36,
			48,
			72
		].toArray())
	}
}