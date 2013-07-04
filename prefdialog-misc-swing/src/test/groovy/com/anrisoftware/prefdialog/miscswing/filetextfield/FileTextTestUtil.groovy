package com.anrisoftware.prefdialog.miscswing.filetextfield

import javax.swing.JButton
import javax.swing.JPanel

import net.miginfocom.swing.MigLayout

import com.anrisoftware.globalpom.utils.TestFrameUtil

/**
 * Utilities to test the file text field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class FileTextTestUtil {

	public static final String FILE_FIELD_NAME = "file-field"

	FileTextField field

	JPanel panel

	TestFrameUtil createFrame(def title) {
		field = createFileField()
		panel = createPanel(field)
		new TestFrameUtil(title, panel)
	}

	JPanel createPanel(def component) {
		def button = new JButton("...")
		def panel = new JPanel(new MigLayout("", "[10][grow,fill][][10]", "[grow,fill][][grow,fill]"))
		panel.add component, "cell 1 1"
		panel.add button, "cell 2 1"
		panel
	}

	FileTextField createFileField() {
		def field = FileTextField.create()
		field.setName FILE_FIELD_NAME
		return field
	}
}
